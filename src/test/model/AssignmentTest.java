package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {

    private Assignment assignment;

    @BeforeEach()
    public void setup() {
        this.assignment = new Assignment("Test Assignment");
        this.assignment.setDescription("Just another assignment.");
        this.assignment.setPoints(5);
        this.assignment.setCompleteByDate(LocalDate.now());
    }

    @Test()
    public void testStaging() {
        assertEquals(AssignmentStage.NEED_TO_START, this.assignment.getStage());
        assertEquals(AssignmentStage.STARTED, this.assignment.stageForward());
        assertEquals(AssignmentStage.HALFWAY, this.assignment.stageForward());
        assertEquals(AssignmentStage.REVIEW, this.assignment.stageForward());
        assertEquals(AssignmentStage.SUBMISSION, this.assignment.stageForward());
        assertEquals(AssignmentStage.COMPLETE, this.assignment.stageForward());
        assertEquals(AssignmentStage.COMPLETE, this.assignment.stageForward());
        assertEquals(AssignmentStage.SUBMISSION, this.assignment.stageBackward());
        assertEquals(AssignmentStage.REVIEW, this.assignment.stageBackward());
        assertEquals(AssignmentStage.HALFWAY, this.assignment.stageBackward());
        assertEquals(AssignmentStage.STARTED, this.assignment.stageBackward());
        assertEquals(AssignmentStage.NEED_TO_START, this.assignment.stageBackward());
        assertEquals(AssignmentStage.NEED_TO_START, this.assignment.stageBackward());
    }

    @Test()
    public void testPercentages() {
        assertEquals(0f, this.assignment.percentageComplete());
        this.assignment.stageForward();
        assertEquals(0.1f, this.assignment.percentageComplete());
        this.assignment.stageForward();
        assertEquals(0.5f, this.assignment.percentageComplete());
        this.assignment.stageForward();
        assertEquals(0.9f, this.assignment.percentageComplete());
        this.assignment.stageForward();
        assertEquals(0.95f, this.assignment.percentageComplete());
        this.assignment.stageForward();
        assertEquals(1f, this.assignment.percentageComplete());
        this.assignment.stageForward();
    }

    @Test()
    public void testDifferentStage() {
        this.assignment = new Assignment("Test Assignment", AssignmentStage.COMPLETE);
        this.assignment.setDescription("Just another assignment.");
        this.assignment.setPoints(5);
        this.assignment.setCompleteByDate(LocalDate.now());
        assertEquals(AssignmentStage.COMPLETE, this.assignment.getStage());
        assertEquals(AssignmentStage.COMPLETE, this.assignment.stageForward());
    }
}
