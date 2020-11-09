package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;

public class GraphicalUserInterface extends JFrame {

    // Get context about program state
    private boolean isRunning;
    private ToDoList toDoList;
    private ErrandList errandList;
    private AssignmentList assignmentList;
    private ExaminableList examinableList;

    // JSON saving stuff
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/todolist.json";

    // Gui variables
    private JSplitPane splitPane;

    // Table and relevant variables
    private JTable table;
    private ToDoListTableModel tableModel;

    /**
     * MODIFIES: this
     * EFFECTS : instiantiates the window
     */
    public GraphicalUserInterface() {
        super("Stuganizer");
        this.setupApplicationState();
        this.setupGraphicalInterface();
    }

    /**
     * Initializes application state in the GUI
     */
    private void setupApplicationState() {
        this.isRunning = true;
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        try {
            this.toDoList = this.jsonReader.read();
        } catch (Exception exception) {
            this.toDoList = new ToDoList(this.askPointsPerDay());
            this.toDoList.setErrandList(new ErrandList());
            this.toDoList.setAssignmentList(new AssignmentList());
            this.toDoList.setExaminableList(new ExaminableList());
        }
        this.errandList = this.toDoList.getErrandList();
        this.assignmentList = this.toDoList.getAssignmentList();
        this.examinableList = this.toDoList.getExaminableList();
    }

    /**
     * EFFECTS: Prompts the user how many points they'd like to complete in a day
     */
    private int askPointsPerDay() {
        return 10;
    }

    /**
     * Setup the graphical interface of the application
     */
    private void setupGraphicalInterface() {
        this.tableModel = new ToDoListTableModel(this.toDoList);
        this.table = new JTable(this.tableModel);
        this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.table, new JPanel());
        this.setContentPane(this.splitPane);
        this.pack();
        this.setVisible(true);
    }

    /**
     * EFFECTS: Save the to do list to the filesystem
     *
     * Implementation taken from
     * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
     */
    private void saveToFileSystemAndClose() {
        try {
            jsonWriter.open();
            jsonWriter.write(this.toDoList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
            this.isRunning = false;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
