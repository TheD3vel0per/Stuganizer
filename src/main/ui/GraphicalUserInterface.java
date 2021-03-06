package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

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
    }

    /**
     * EFFECTS: Returns the data table model
     */
    public ToDoListTableModel getTableModel() {
        return this.tableModel;
    }

    /**
     * EFFECTS: Returns the ToDoList
     */
    public ToDoList getToDoList() {
        return this.toDoList;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Refreshes the top portion of the screen (table)
     */
    public void refreshTable() {
        this.setupTablePanel();
    }

    /**
     * MODIFIES: this
     * EFFECTS : Refreshes the bottom portion of the screen
     */
    public void refreshPanel() {
        this.mainWindow.showTaskInPanel(this.selectedTask);
    }

    /**
     * MODIFIES: this
     * EFFECTS : Set selected task
     */
    public void setSelectedTask(Task task) {
        this.selectedTask = task;
        this.refreshPanel();
    }

    /**
     * EFFECTS: returns the current selected task
     */
    public Task getSelectedTask() {
        return this.selectedTask;
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
            this.errandList = this.toDoList.getErrandList();
            this.assignmentList = this.toDoList.getAssignmentList();
            this.examinableList = this.toDoList.getExaminableList();
            this.setupGraphicalInterface();
        } catch (Exception exception) {
            this.toDoList = new ToDoList(1);
            this.askPointsPerDay();
            this.errandList = this.toDoList.getErrandList();
            this.assignmentList = this.toDoList.getAssignmentList();
            this.examinableList = this.toDoList.getExaminableList();
        }
    }

    /**
     * EFFECTS: Prompts the user how many points they'd like to complete in a day
     */
    private void askPointsPerDay() {
        PointChooser pointChooser = new PointChooser(this.toDoList, this);
        pointChooser.pack();
        pointChooser.setVisible(true);
    }

    /**
     * MODIFIES: this
     * EFFECTS : Sets up the graphical interface of the application
     */
    public void setupGraphicalInterface() {
        this.setupTablePanel();
        this.mainWindow = new MainWindow(this);
        this.mainWindow.setBounds(0, 0, 600, 400);
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
    public void saveToFileSystemAndClose() {
        playGoodbyeSound();
        try {
            jsonWriter.open();
            jsonWriter.write(this.toDoList);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
            this.isRunning = false;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        System.exit(0);
    }

    /**
     * EFFECTS: Plays the goodbye sound
     */
    private void playGoodbyeSound() {
        try {
            File audioFile = new File("./data/quitbtn.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
}
