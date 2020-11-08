package persistence;

import model.AssignmentList;
import model.ErrandList;
import model.ExaminableList;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * These tests were retrieved from:
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
 *
 * and then modified to fit the needs of the application.
 */
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList toDoList = new ToDoList(10);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyToDoList() {
        try {
            ToDoList toDoList = new ToDoList(5);
            toDoList.setErrandList(new ErrandList());
            toDoList.setAssignmentList(new AssignmentList());
            toDoList.setExaminableList(new ExaminableList());
            JsonWriter writer = new JsonWriter("./data/test_writerEmptyWorkroom.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/test_writerEmptyWorkroom.json");
            ToDoList toDoListRead = reader.read();
            assertEquals(-1, toDoListRead.getErrandList().maxIndex());
            assertEquals(0, toDoListRead.getErrandList().size());
            assertEquals(-1, toDoListRead.getAssignmentList().maxIndex());
            assertEquals(0, toDoListRead.getAssignmentList().size());
            assertEquals(5, toDoListRead.getPointsPerDay());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralToDoList() {
        try {
            ToDoList toDoList = new ToDoList(10);
            toDoList.setErrandList(this.genErrandList());
            toDoList.setAssignmentList(this.genAssignmentList());
            toDoList.setExaminableList(this.genExaminableList());
            JsonWriter writer = new JsonWriter("./data/test_writerGeneralWorkroom.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/test_writerGeneralWorkroom.json");
            ToDoList toDoListRead = reader.read();
            ErrandList errands = toDoListRead.getErrandList();
            assertEquals(2, errands.size());
            assertEquals(1, errands.maxIndex());
            checkErrand1(errands.get(0));
            checkErrand2(errands.get(1));

            AssignmentList assignments = toDoListRead.getAssignmentList();
            assertEquals(1, assignments.size());
            assertEquals(0, assignments.maxIndex());
            checkAssignment(assignments.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
