package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * These tests were retrieved from:
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
 *
 * and then modified to fit the needs of the application.
 */
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList toDoList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/empty_todolist.json");
        try {
            ToDoList toDoList = reader.read();
            assertEquals(-1, toDoList.getErrandList().maxIndex());
            assertEquals(0, toDoList.getErrandList().size());
            assertEquals(-1, toDoList.getAssignmentList().maxIndex());
            assertEquals(0, toDoList.getAssignmentList().size());
            assertEquals(5, toDoList.getPointsPerDay());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/test_todolist.json");
        try {
            ToDoList toDoList = reader.read();
            assertEquals(10, toDoList.getPointsPerDay());

            ErrandList errands = toDoList.getErrandList();
            assertEquals(2, errands.size());
            assertEquals(1, errands.maxIndex());
            checkErrand1(errands.get(0));
            checkErrand2(errands.get(1));

            AssignmentList assignments = toDoList.getAssignmentList();
            assertEquals(1, assignments.size());
            assertEquals(0, assignments.maxIndex());
            checkAssignment(assignments.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
