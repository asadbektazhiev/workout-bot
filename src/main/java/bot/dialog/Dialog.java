package bot.dialog;

public interface Dialog {
    String start();
    String answer(String input);
    boolean isFinished();
}