package persistence;

import model.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * This code was inspired by:
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
 */
public class JsonTest {
    protected void checkErrand1(Errand errandToCheck) {
        assertEquals(2, errandToCheck.getPoints());
        assertEquals("Grocery Shopping", errandToCheck.getTitle());
        assertFalse(errandToCheck.isComplete());
        assertEquals("Need to buy more milk, bread, and eggs.", errandToCheck.getDescription());
        assertEquals("2021-12-31", errandToCheck.getCompleteByDate().toString());
    }

    protected void checkErrand2(Errand errandToCheck) {
        assertEquals(3, errandToCheck.getPoints());
        assertEquals("Clean the Kitchen", errandToCheck.getTitle());
        assertTrue(errandToCheck.isComplete());
        assertEquals("Clean the fridge and mop the floor.", errandToCheck.getDescription());
        assertEquals("2021-12-31", errandToCheck.getCompleteByDate().toString());
    }

    protected void checkAssignment(Assignment assignmentToCheck) {
        assertEquals(4, assignmentToCheck.getPoints());
        assertEquals("Math 226 Webwork", assignmentToCheck.getTitle());
        assertFalse(assignmentToCheck.isComplete());
        assertEquals("I need to complete a couple of questions", assignmentToCheck.getDescription());
        assertEquals("2021-12-31", assignmentToCheck.getCompleteByDate().toString());
        assertEquals("Need to Start", assignmentToCheck.getStage().toString());
    }

    protected ErrandList genErrandList() {
        ErrandList errandList = new ErrandList();

        Errand errand1 = new Errand("Grocery Shopping", LocalDate.parse("2021-12-31"));
        errand1.setPoints(2);
        errand1.setDescription("Need to buy more milk, bread, and eggs.");
        errandList.add(errand1);

        Errand errand2 = new Errand("Clean the Kitchen", LocalDate.parse("2021-12-31"));
        errand2.setPoints(3);
        errand2.setDescription("Clean the fridge and mop the floor.");
        errand2.markCompleted();
        errandList.add(errand2);

        return errandList;
    }

    protected AssignmentList genAssignmentList() {
        AssignmentList assignmentList = new AssignmentList();

        Assignment assignment = new Assignment("Math 226 Webwork");
        assignment.setCompleteByDate(LocalDate.parse("2021-12-31"));
        assignment.setPoints(4);
        assignment.setDescription("I need to complete a couple of questions");
        assignmentList.add(assignment);

        return assignmentList;
    }

    protected ExaminableList genExaminableList() {
        ExaminableList examinableList = new ExaminableList();

        Examinable examinable = new Examinable("CPSC 210 Midterm");
        examinable.setCompleteByDate(LocalDate.parse("2021-11-18"));
        examinable.setPoints(4);
        examinable.setDescription("I need to do well on this exam");
        examinableList.add(examinable);

        return examinableList;
    }
}
