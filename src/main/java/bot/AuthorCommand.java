package bot;

public class AuthorCommand implements Command {
    @Override
    public String name() {
        return "author";
    }

    @Override
    public String description() {
        return "Информация об авторах";
    }

    @Override
    public String help() {
        return "/author — показывает, кто создал бота.";
    }

    @Override
    public String execute(String[] args) {
        return "Авторы: Тажиев Асадбек (группа МЕН-251015).";
    }
}