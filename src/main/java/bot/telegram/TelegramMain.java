package bot.telegram;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class TelegramMain {
    public static void main(String[] args) throws Exception {
        String token = System.getenv("BOT_TOKEN");
        if (token == null || token.isBlank()) {
            System.out.println("Задайте переменную окружения BOT_TOKEN");
            return;
        }
        TelegramBotsLongPollingApplication application = new TelegramBotsLongPollingApplication();
        application.registerBot(token, new TelegramBot(new OkHttpTelegramClient(token)));
        System.out.println("Бот запущен");
        Thread.currentThread().join();
    }
}