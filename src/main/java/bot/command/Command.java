package bot.command;

public interface Command {
    String name();                    // имя без "/", например "author"
    String description();             // короткое описание для /help
    String help();                    // подробная справка для /help <команда>
    String execute(String[] args);    // выполнение команды
}