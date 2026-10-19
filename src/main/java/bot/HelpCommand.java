package bot;

import java.util.Optional;

public class HelpCommand implements Command {
    private final CommandRegistry registry;

    public HelpCommand(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "Список команд или справка по одной команде";
    }

    @Override
    public String help() {
        return "/help — все команды.\n/help <команда> — справка по команде, например: /help plan";
    }

    @Override
    public String execute(String[] args) {
        if (args.length == 0) {
            return allCommands();
        }
        String name = args[0].replaceFirst("^/", "").toLowerCase();
        Optional<Command> command = registry.find(name);
        return command.map(Command::help)
                .orElse("Команды /" + name + " не существует. Список: /help");
    }

    private String allCommands() {
        StringBuilder sb = new StringBuilder("Доступные команды:\n");
        for (Command command : registry.all()) {
            sb.append("/").append(command.name())
                    .append(" — ").append(command.description()).append("\n");
        }
        sb.append("Подробнее о команде: /help <команда>");
        return sb.toString();
    }
}