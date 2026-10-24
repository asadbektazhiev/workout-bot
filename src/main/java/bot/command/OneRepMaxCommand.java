package bot.command;

import java.util.Locale;

public class OneRepMaxCommand implements Command {
    @Override
    public String name() {
        return "1rm";
    }

    @Override
    public String description() {
        return "Расчёт максимального веса на 1 повторение";
    }

    @Override
    public String help() {
        return "/1rm <вес> <повторы> — считает ваш максимум на 1 раз по формуле Эпли.\n"
                + "Пример: /1rm 80 8";
    }

    @Override
    public String execute(String[] args) {
        if (args.length != 2) {
            return "Нужно два числа: вес и количество повторов. Пример: /1rm 80 8";
        }
        double weight;
        int reps;
        try {
            weight = Double.parseDouble(args[0].replace(',', '.'));
            reps = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            return "Не удалось разобрать числа. Пример: /1rm 80 8";
        }
        if (weight <= 0 || reps < 1 || reps > 30) {
            return "Вес должен быть больше 0, повторы — от 1 до 30.";
        }

        double oneRm = weight * (1 + reps / 30.0);
        return String.format(Locale.ROOT,
                "Ваш максимум на 1 повторение: %.1f кг.\n"
                        + "Рабочие веса: 90%% — %.1f кг, 80%% — %.1f кг, 70%% — %.1f кг.",
                oneRm, oneRm * 0.9, oneRm * 0.8, oneRm * 0.7);
    }
}