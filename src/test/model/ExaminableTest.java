package model;

import model.exceptions.CannotStageTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ExaminableTest {

    private Examinable examinable;

    @BeforeEach()
    public void setup() {
        this.examinable = new Examinable("Test Examinable");
        this.examinable.setDescription("Just another examinable.");
        this.examinable.setPoints(5);
        this.examinable.setCompleteByDate(LocalDate.now());
    }

    @Test()
    public void testMarkCompleteToday() {
        assertEquals(0f, this.examinable.percentageComplete());
        assertFalse(this.examinable.isComplete());
        try {
            this.examinable.markCompleted();
            assertEquals(1f, this.examinable.percentageComplete());
            assertTrue(this.examinable.isComplete());
        } catch (CannotStageTask cannotStageTask) {
            fail("CannotStageTask should not have been thrown.");
        }
    }

    @Test()
    public void testMarkCompleteNotToday() {
        this.examinable.setCompleteByDate(LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + 1));
        assertEquals(0f, this.examinable.percentageComplete());
        assertFalse(this.examinable.isComplete());
        try {
            this.examinable.markCompleted();
            fail("CannotStageTask should not have been thrown.");
        } catch (CannotStageTask cannotStageTask) {
            assertEquals(0f, this.examinable.percentageComplete());
            assertFalse(this.examinable.isComplete());
        }
    }
}
