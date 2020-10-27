package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssignmentStageTest {

    @Test()
    void testToString() {
        assertEquals("Need to Start", AssignmentStage.NEED_TO_START.toString());
        assertEquals("Started", AssignmentStage.STARTED.toString());
        assertEquals("Halfway", AssignmentStage.HALFWAY.toString());
        assertEquals("Review", AssignmentStage.REVIEW.toString());
        assertEquals("Send to Submit", AssignmentStage.SUBMISSION.toString());
        assertEquals("Complete!", AssignmentStage.COMPLETE.toString());
    }

    @Test()
    void testFromString() {
        assertEquals(AssignmentStage.NEED_TO_START, AssignmentStage.fromString("Need to Start"));
        assertEquals(AssignmentStage.STARTED, AssignmentStage.fromString("Started"));
        assertEquals(AssignmentStage.HALFWAY, AssignmentStage.fromString("Halfway"));
        assertEquals(AssignmentStage.REVIEW, AssignmentStage.fromString("Review"));
        assertEquals(AssignmentStage.SUBMISSION, AssignmentStage.fromString("Send to Submit"));
        assertEquals(AssignmentStage.COMPLETE, AssignmentStage.fromString("Complete!"));
    }
}
