package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    private ToDoList toDoList;
    private ErrandList errandList;
    private AssignmentList assignmentList;
    private final int pointsPerDay = 10;
    private Random random;

    @BeforeEach()
    public void setup() {
        this.random = new Random();
        this.toDoList = new ToDoList(this.pointsPerDay);
        this.errandList = new ErrandList();
        this.assignmentList = new AssignmentList();
        this.toDoList.setErrandList(this.errandList);
        this.toDoList.setAssignmentList(this.assignmentList);
    }

    public ArrayList<Errand> addErrandToErrandList(int quantity) {
        ArrayList<Errand> errands = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Errand errandToAdd = new Errand("Test Errand #" + i);
            errandToAdd.setPoints(
                    Task.MIN_POINTS + this.random.nextInt(Task.MAX_POINTS - Task.MIN_POINTS)
            );
            this.errandList.add(errandToAdd);
            errands.add(errandToAdd);
        }
        return errands;
    }

    public ArrayList<Assignment> addErrandToAssignmentList(int quantity) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Assignment assignmentToAdd = new Assignment("Test Assignment #" + i);
            assignmentToAdd.setPoints(
                    Task.MIN_POINTS + this.random.nextInt(Task.MAX_POINTS - Task.MIN_POINTS)
            );
            this.assignmentList.add(assignmentToAdd);
            assignments.add(assignmentToAdd);
        }
        return assignments;
    }

    @Test()
    public void testEmpty() {
        assertEquals(this.errandList, this.toDoList.getErrandList());
        assertEquals(0, this.toDoList.getTasksForToday().size());
        assertEquals(0, this.toDoList.getAllTasks().size());
    }

    @Test()
    public void testOneErrand() {
        List<Errand> errands = this.addErrandToErrandList(1);
        Errand errand = errands.get(0);
        errand.setCompleteByDate(LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + 1));

        assertEquals(errand, this.toDoList.getTasksForToday().get(0));
        assertEquals(1, this.toDoList.getTasksForToday().size());
        assertEquals(errand, this.toDoList.getAllTasks().get(0));
        assertEquals(1, this.toDoList.getAllTasks().size());
        assertEquals(0, this.toDoList.getPointsAwarded());

        errand.markCompleted();

        assertTrue(this.toDoList.markTaskCompleted(errand));
        assertEquals(errand.getPoints(), this.toDoList.getPointsAwarded());
        assertEquals(1, this.toDoList.getTasksCompletedToday().size());
        assertEquals(errand, this.toDoList.getTasksCompletedToday().get(0));

        Errand incompleteErrand = this.addErrandToErrandList(1).get(0);
        assertFalse(this.toDoList.markTaskCompleted(incompleteErrand));
        assertEquals(errand.getPoints(), this.toDoList.getPointsAwarded());
        assertEquals(1, this.toDoList.getTasksCompletedToday().size());
        assertEquals(errand, this.toDoList.getTasksCompletedToday().get(0));
    }

    @Test
    public void testOneAssignment() {
        List<Assignment> assignments = this.addErrandToAssignmentList(1);
        Assignment assignment = assignments.get(0);
        assignment.setCompleteByDate(LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + 1));

        assertEquals(assignment, this.toDoList.getTasksForToday().get(0));
        assertEquals(1, this.toDoList.getTasksForToday().size());
        assertEquals(assignment, this.toDoList.getAllTasks().get(0));
        assertEquals(1, this.toDoList.getAllTasks().size());
        assertEquals(0, this.toDoList.getPointsAwarded());

        assignment.stageForward();

        assertEquals(assignment, this.toDoList.getTasksForToday().get(0));
        assertEquals(1, this.toDoList.getTasksForToday().size());
        assertEquals(assignment, this.toDoList.getAllTasks().get(0));
        assertEquals(1, this.toDoList.getAllTasks().size());
        assertEquals(0, this.toDoList.getPointsAwarded());
    }

    @Test()
    public void testNextDate() {
        List<Errand> errands = this.addErrandToErrandList(1);
        Errand errand = errands.get(0);
        errand.setCompleteByDate(LocalDate.ofEpochDay(LocalDate.now().toEpochDay() + 1));
        errand.markCompleted();

        assertTrue(this.toDoList.markTaskCompleted(errand));
        assertEquals(errand.getPoints(), this.toDoList.getPointsAwarded());
        assertEquals(1, this.toDoList.getTasksCompletedToday().size());
        assertEquals(errand, this.toDoList.getTasksCompletedToday().get(0));

        this.toDoList.nullifyDate();

        assertEquals(0, this.toDoList.getPointsAwarded());
        assertEquals(0, this.toDoList.getTasksCompletedToday().size());
    }

}
