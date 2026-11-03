package bot.telegram;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SessionRouterTest {
    @Test
    void startShowsGreeting() {
        assertTrue(new SessionRouter().reply(1, "/start").contains("Привет"));
    }

    @Test
    void chatsHaveSeparateDialogs() {
        SessionRouter router = new SessionRouter();
        router.reply(1, "/plan");
        assertTrue(router.reply(2, "1").contains("только команды"));
        assertTrue(router.reply(1, "1").contains("Где будете заниматься"));
    }
}