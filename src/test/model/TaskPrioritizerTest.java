package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskPrioritizerTest {

    private TaskPrioritizer taskPrioritizer;
    private Task a;
    private Task b;

    @BeforeEach()
    public void setup() {
        this.taskPrioritizer = new TaskPrioritizer();
        this.a = new Errand();
        this.b = new Errand();
    }

    @Test()
    public void testNulled() {
        this.a = Errand.NULL_ERRAND;
        this.b = Errand.NULL_ERRAND;

        this.taskPrioritizer.compare(a, b);
    }

    @Test()
    public void testDiffDays() {
        a.setCompleteByDate(LocalDate.now());
        b.setCompleteByDate(LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + 1));
        assertEquals(-1, this.taskPrioritizer.compare(a, b));
        assertEquals(1, this.taskPrioritizer.compare(b, a));
    }

    @Test()
    public void testDiffPoints() {
        a.setPoints(5);
        b.setPoints(4);
        assertEquals(-1, this.taskPrioritizer.compare(a, b));
        assertEquals(1, this.taskPrioritizer.compare(b, a));
    }

    @Test()
    public void testNoneOfAll() {
        a.setCompleteByDate(LocalDate.now());
        b.setCompleteByDate(LocalDate.now());
        a.setPoints(5);
        b.setPoints(5);
        assertEquals(0, this.taskPrioritizer.compare(a, b));
    }

}
