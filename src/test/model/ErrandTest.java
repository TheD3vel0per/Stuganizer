package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class ErrandTest {

    private Errand errand;
    private String title;
    private String description;
    private LocalDate completeByDate;
    private int points;

    @BeforeEach()
    public void setup() {
        this.errand = new Errand();
        this.title = "Test Errand";
        this.description = "";
        this.completeByDate = LocalDate.of(2021, 12, 31);
    }

    @Test()
    public void checkGettersAndSetters() {
        this.errand.setTitle(this.title);
        assertEquals(this.title, this.errand.getTitle());

        this.errand.setDescription(this.description);
        assertEquals(this.description, this.errand.getDescription());

        this.errand.setCompleteByDate(this.completeByDate);
        assertEquals(this.completeByDate, this.errand.getCompleteByDate());

        int daysUntil = (int) ChronoUnit.DAYS.between(this.completeByDate, LocalDate.now());
        assertEquals(daysUntil, this.errand.daysUntilCompleteByDate());

        assertFalse(this.errand.isComplete());
        this.errand.markCompleted();
        assertTrue(this.errand.isComplete());
    }

    @Test()
    public void checkPoints() {
        int lower = Task.MIN_POINTS;
        int upper = Task.MAX_POINTS + 1;

        assertTrue(this.errand.setPoints(lower));
        assertEquals(lower, this.errand.getPoints());

        assertFalse(this.errand.setPoints(upper));
        assertEquals(lower, this.errand.getPoints());
    }

    @Test()
    public void checkConstructors() {
        String title = "Test Errand Two";
        this.errand = new Errand(title);
        assertEquals(title, this.errand.getTitle());

        this.errand = new Errand(title, this.completeByDate);
        assertEquals(this.completeByDate, this.errand.getCompleteByDate());
        assertEquals(title, this.errand.getTitle());
    }

}
