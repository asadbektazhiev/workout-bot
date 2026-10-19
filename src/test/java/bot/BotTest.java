package bot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BotTest {
    private Bot bot;

    @BeforeEach
    void setUp() {
        bot = Bot.createDefault();
    }

    @Test
    void helpListsAllCommands() {
        String reply = bot.handle("/help");
        for (String name : new String[]{"author", "about", "help", "plan", "1rm"}) {
            assertTrue(reply.contains("/" + name), "В /help нет команды " + name);
        }
    }

    @Test
    void helpForSingleCommand() {
        assertTrue(bot.handle("/help 1rm").contains("формуле Эпли"));
    }

    @Test
    void helpForUnknownCommand() {
        assertTrue(bot.handle("/help qwerty").contains("не существует"));
    }

    @Test
    void unknownCommandGivesHint() {
        assertTrue(bot.handle("/qwerty").contains("Неизвестная команда"));
    }

    @Test
    void textWithoutSlashIsRejected() {
        assertTrue(bot.handle("привет").contains("только команды"));
    }

    @Test
    void oneRepMaxIsCalculated() {
        assertTrue(bot.handle("/1rm 80 8").contains("101.3"));
    }

    @Test
    void oneRepMaxRejectsBadInput() {
        assertTrue(bot.handle("/1rm abc").contains("Нужно два числа"));
    }

    @Test
    void planDialogFullScenario() {
        assertTrue(bot.handle("/plan").contains("цель"));
        assertTrue(bot.handle("1").contains("Где будете заниматься"));
        assertTrue(bot.handle("1").contains("Сколько раз"));
        String plan = bot.handle("3");
        assertTrue(plan.contains("Пн") && plan.contains("набор массы"));
    }

    @Test
    void planDialogRepeatsQuestionOnBadAnswer() {
        bot.handle("/plan");
        String reply = bot.handle("9");
        assertTrue(reply.contains("от 1 до 3") && reply.contains("Какая у вас цель"));
    }

    @Test
    void cancelStopsDialog() {
        bot.handle("/plan");
        bot.handle("/cancel");
        assertFalse(bot.handle("/author").isEmpty());
        assertTrue(bot.handle("/author").contains("Автор"));
    }
}