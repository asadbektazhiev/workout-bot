package bot.telegram;

import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class TelegramBot implements LongPollingSingleThreadUpdateConsumer {
    private final TelegramClient client;
    private final SessionRouter router = new SessionRouter();

    public TelegramBot(TelegramClient client) {
        this.client = client;
    }

    @Override
    public void consume(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        long chatId = update.getMessage().getChatId();
        String reply = router.reply(chatId, update.getMessage().getText());
        send(chatId, reply);
    }

    private void send(long chatId, String text) {
        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        try {
            client.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}