package bot;

import bot.command.*;
import bot.dialog.Dialog;
import bot.dialog.DialogCommand;
import bot.dialog.PlanCommand;

import java.util.Arrays;
import java.util.Optional;

public class Bot {
    private final CommandRegistry registry;
    private Dialog currentDialog;

    public Bot(CommandRegistry registry) {
        this.registry = registry;
    }

    public static Bot createDefault() {
        CommandRegistry registry = new CommandRegistry();
        registry.register(new AuthorCommand());
        registry.register(new AboutCommand());
        registry.register(new PlanCommand());
        registry.register(new OneRepMaxCommand());
        registry.register(new HelpCommand(registry));
        return new Bot(registry);
    }

    public String greeting() {
        return "Привет! Я бот-тренер. Помогаю подобрать программу тренировок "
                + "и считать рабочие веса.\n"
                + "/plan — подобрать программу (я задам несколько вопросов)\n"
                + "/1rm — посчитать максимум на 1 повторение\n"
                + "/help — список всех команд";
    }

    public String handle(String input) {
        String text = input.trim();
        if (text.isEmpty()) {
            return "Введите команду. Список команд: /help";
        }

        if (currentDialog != null) {
            return continueDialog(text);
        }

        if (!text.startsWith("/")) {
            return "Я понимаю только команды, они начинаются с «/». Список: /help";
        }

        String[] parts = text.substring(1).split("\\s+");
        String name = parts[0].toLowerCase();
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        Optional<Command> command = registry.find(name);
        if (command.isEmpty()) {
            return "Неизвестная команда /" + name + ". Список команд: /help";
        }

        Command found = command.get();
        if (found instanceof DialogCommand dialogCommand) {
            currentDialog = dialogCommand.createDialog();
            return currentDialog.start();
        }
        return found.execute(args);
    }

    private String continueDialog(String text) {
        if (text.equalsIgnoreCase("/cancel")) {
            currentDialog = null;
            return "Диалог отменён.";
        }
        String reply = currentDialog.answer(text);
        if (currentDialog.isFinished()) {
            currentDialog = null;
        }
        return reply;
    }
}