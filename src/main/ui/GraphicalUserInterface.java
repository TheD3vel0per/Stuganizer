package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * https://stackoverflow.com/questions/10128064/jtable-selected-row-click-event
 */
public class GraphicalUserInterface {

    // Get context about program state
    private boolean isRunning;
    private ToDoList toDoList;
    private ErrandList errandList;
    private AssignmentList assignmentList;
    private ExaminableList examinableList;
    private Task selectedTask;

    // JSON saving stuff
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/todolist.json";

    // Gui variables
    private MainWindow mainWindow;

    // Table and relevant variables
    private JTable table;
    private ToDoListTableModel tableModel;

    /**
     * MODIFIES: this
     * EFFECTS : instiantiates the window
     */
    public GraphicalUserInterface() {
        this.setupApplicationState();
        this.setupGraphicalInterface();
    }

    /**
     * EFFECTS: Returns the data table model
     */
    public ToDoListTableModel getTableModel() {
        return this.tableModel;
    }

    /**
     * EFFECTS: Set selected task
     */
    public void setSelectedTask(Task task) {
        this.selectedTask = task;
        this.mainWindow.showTaskInPanel(task);
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
     * MODIFIES: this
     * EFFECTS : Sets up the graphical interface of the application
     */
    private void setupGraphicalInterface() {
        this.setupTablePanel();
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setVisible(true);
    }

    /**
     * MODIFIES: this
     * EFFECTS : Sets up the table for the top panel
     */
    private void setupTablePanel() {
        this.tableModel = new ToDoListTableModel(this.toDoList);
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
