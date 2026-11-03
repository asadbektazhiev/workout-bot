package bot.command;

public interface Command {
    String name();
    String description();
    String help();
    String execute(String[] args);
}