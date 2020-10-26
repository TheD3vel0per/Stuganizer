package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentListTest {

    private AssignmentList assignmentList;
    private Random random;
    private int maxListSize;

    @BeforeEach()
    public void setup() {
        this.assignmentList = new AssignmentList();
        this.random = new Random();
        this.maxListSize = 3;
    }

    public ArrayList<Assignment> addAssignmentToList(int quantity) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Assignment assignmentToAdd = new Assignment("Test Assignment #" + i);
            this.assignmentList.add(assignmentToAdd);
            assignments.add(assignmentToAdd);
        }
        return assignments;
    }

    @Test()
    public void testEmptyList() {
        assertEquals(0, this.assignmentList.size());
        assertEquals(-1, this.assignmentList.maxIndex());
        assertTrue(this.assignmentList.isEmpty());
    }

    @Test()
    public void testFilledAssignmentList() {
        int assignmentListSize = random.nextInt(maxListSize - 1) + 1;
        ArrayList<Assignment> assignments = this.addAssignmentToList(assignmentListSize);
        assertEquals(assignments.size(), this.assignmentList.size());
        for (int i = 0; i <= this.assignmentList.maxIndex(); i++) {
            assertEquals(assignments.get(i), this.assignmentList.get(i));
        }
    }

    @Test()
    public void testRemoving() {
        this.addAssignmentToList(this.maxListSize);

        assertEquals(this.maxListSize, this.assignmentList.size());
        assertEquals(this.maxListSize - 1, this.assignmentList.maxIndex());

        int indexToRemove = this.random.nextInt(this.maxListSize);
        this.assignmentList.remove(indexToRemove);
        assertEquals(Assignment.NULL_ASSIGNMENT, this.assignmentList.get(indexToRemove));
        assertEquals(this.maxListSize - 1, this.assignmentList.size());
        assertEquals(this.maxListSize - 1, this.assignmentList.maxIndex());

    }

    @Test()
    public void testGetOutOfRange() {
        assertNull(this.assignmentList.get(this.assignmentList.maxIndex() + 1));
    }
}
