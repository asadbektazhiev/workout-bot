package handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandlerTest {
    private ConsoleApp app;

    @BeforeEach
    void setUp() {
        app = new ConsoleApp();
        app.register(new ExerciseNameHandler());
        app.register(new UpperCaseHandler());
        app.register(new CamelCaseHandler());
    }

    @Test
    void defaultHandlerFormatsExerciseName() {
        assertEquals("Жим Лежа", app.process("жим ЛЕЖА"));
    }

    @Test
    void upperCaseHandler() {
        assertEquals("ЖИМ ЛЕЖА", new UpperCaseHandler().handle("жим лежа"));
    }

    @Test
    void camelCaseHandler() {
        assertEquals("жимЛежа", new CamelCaseHandler().handle("Жим  ЛЕЖА"));
    }

    @Test
    void modeSwitchChangesHandler() {
        assertEquals("Режим: upper", app.process("/mode upper"));
        assertEquals("ЖИМ ЛЕЖА", app.process("жим лежа"));
        assertEquals("Режим: camel", app.process("/mode camel"));
        assertEquals("жимЛежа", app.process("жим лежа"));
    }

    @Test
    void unknownModeIsRejected() {
        assertTrue(app.process("/mode qwerty").contains("Нет такого режима"));
    }

    @Test
    void modesListShowsAllHandlers() {
        String reply = app.process("/modes");
        assertTrue(reply.contains("exercise") && reply.contains("upper") && reply.contains("camel"));
    }
}