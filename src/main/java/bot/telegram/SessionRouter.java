package bot.telegram;

import bot.Bot;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionRouter {
    private final Map<Long, Bot> bots = new ConcurrentHashMap<>();

    public String reply(long chatId, String text) {
        Bot bot = bots.computeIfAbsent(chatId, id -> Bot.createDefault());
        if (text.trim().equalsIgnoreCase("/start")) {
            return bot.greeting();
        }
        return bot.handle(text);
    }
}