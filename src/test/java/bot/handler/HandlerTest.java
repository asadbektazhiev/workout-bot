package bot.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HandlerTest {
    @Test
    void formatsExerciseName() {
        assertEquals("Жим Лежа", new ExerciseNameHandler().handle("жим ЛЕЖА"));
    }

    @Test
    void ignoresExtraSpaces() {
        assertEquals("Жим Лежа", new ExerciseNameHandler().handle("  жим   лежа "));
    }
}