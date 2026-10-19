package bot;

public class AboutCommand implements Command {
    @Override
    public String name() {
        return "about";
    }

    @Override
    public String description() {
        return "О назначении бота";
    }

    @Override
    public String help() {
        return "/about — рассказывает, зачем нужен бот.";
    }

    @Override
    public String execute(String[] args) {
        return "Я бот-тренер. Задаю несколько вопросов о вашей цели и условиях "
                + "и составляю программу тренировок (/plan). Ещё умею считать "
                + "максимальный вес на одно повторение (/1rm).";
    }
}