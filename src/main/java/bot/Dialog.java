package bot;

public interface Dialog {
    String start();               // первый вопрос
    String answer(String input);  // реакция на ответ пользователя
    boolean isFinished();         // закончен ли диалог
}