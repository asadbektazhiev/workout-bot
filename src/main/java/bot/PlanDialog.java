package bot;

public class PlanDialog implements Dialog {
    private enum Step { GOAL, PLACE, DAYS, DONE }

    private static final String[] GOALS = {"набор массы", "похудение", "сила"};
    private static final String[] SCHEME = {
            "3-4 подхода по 8-12 повторов, отдых 60-90 секунд",
            "3 подхода по 12-15 повторов, отдых 30-60 секунд + 2 кардио по 20-30 минут",
            "4-5 подходов по 3-5 повторов, отдых 2-3 минуты"
    };
    private static final String[][] DAY_NAMES = {
            {"Всё тело A", "Всё тело B"},
            {"Грудь, плечи, трицепс", "Спина, бицепс", "Ноги, пресс"},
            {"Верх (жимы)", "Низ (приседания)", "Верх (тяги)", "Низ (становая)"},
            {"Грудь, трицепс", "Спина, бицепс", "Ноги", "Плечи, пресс", "Всё тело"}
    };
    private static final String[][] WEEKDAYS = {
            {"Пн", "Чт"},
            {"Пн", "Ср", "Пт"},
            {"Пн", "Вт", "Чт", "Пт"},
            {"Пн", "Вт", "Ср", "Пт", "Сб"}
    };

    private Step step = Step.GOAL;
    private int goal;
    private int place;

    @Override
    public String start() {
        return "Подберём программу. Отмена: /cancel\n" + question();
    }

    @Override
    public String answer(String input) {
        int number = parse(input);
        switch (step) {
            case GOAL -> {
                if (number < 1 || number > 3) {
                    return "Введите число от 1 до 3.\n" + question();
                }
                goal = number;
                step = Step.PLACE;
            }
            case PLACE -> {
                if (number < 1 || number > 2) {
                    return "Введите 1 или 2.\n" + question();
                }
                place = number;
                step = Step.DAYS;
            }
            case DAYS -> {
                if (number < 2 || number > 5) {
                    return "Введите число от 2 до 5.\n" + question();
                }
                step = Step.DONE;
                return buildPlan(number);
            }
            default -> {
                return "";
            }
        }
        return question();
    }

    @Override
    public boolean isFinished() {
        return step == Step.DONE;
    }

    private String question() {
        return switch (step) {
            case GOAL -> "Какая у вас цель? 1 - набор массы, 2 - похудение, 3 - сила";
            case PLACE -> "Где будете заниматься? 1 - зал, 2 - дома";
            case DAYS -> "Сколько раз в неделю готовы тренироваться? (2-5)";
            case DONE -> "";
        };
    }

    private int parse(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String buildPlan(int days) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ваша программа (цель: ").append(GOALS[goal - 1])
                .append(", ").append(place == 1 ? "зал" : "дома").append("):\n");

        String[] names = DAY_NAMES[days - 2];
        String[] weekdays = WEEKDAYS[days - 2];
        for (int i = 0; i < days; i++) {
            sb.append(weekdays[i]).append(" — ").append(names[i]).append("\n");
        }

        sb.append("Схема: ").append(SCHEME[goal - 1]).append(".\n");
        sb.append(place == 1
                ? "Инвентарь: штанга, гантели, тренажёры."
                : "Инвентарь: турник, гантели или резинки, свой вес.");
        sb.append("\nПосчитать рабочие веса: /1rm");
        return sb.toString();
    }
}