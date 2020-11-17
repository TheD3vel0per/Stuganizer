package ui;

import model.*;
import org.jdatepicker.DateModel;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDate;

public class TaskMakerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JSpinner pointsSpinner;
    private JDatePicker completeByDatePicker;

    private GraphicalUserInterface gui;
    private Object type;

    /**
     * REQUIRES: type is a class of Errand, Assignment, Examinable
     * MODIFIES: this
     * EFFECTS : Instantiates the task maker dialog
     */
    public TaskMakerDialog(GraphicalUserInterface gui, Object type) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.gui = gui;
        this.type = type;

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * MODIFIES: this
     * EFFECTS : Event handler for when the okay button is pressed
     */
    private void onOK() {
        ToDoList toDoList = this.gui.getToDoList();
        if (Errand.class.equals(this.type)) {
            this.createErrand(toDoList);
        } else if (Assignment.class.equals(this.type)) {
            this.createAssignment(toDoList);
        } else if (Examinable.class.equals(this.type)) {
            this.createExaminable(toDoList);
        }
        this.updateGui(this.gui);
        dispose();
    }

    /**
     * MODIFIES: this
     * EFFECTS : Closes the dialog when the cancel button is pressed
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * REQUIRES: toDoList.getErrandList() != null
     * MODIFIES: this, toDoList
     * EFFECTS : Creates an errand in the to do list, and updates the table in the GUI
     */
    private void createErrand(ToDoList toDoList) {
        ErrandList errandList = toDoList.getErrandList();
        Errand errand = new Errand();
        this.constructTask(errand);
        errandList.add(errand);
    }

    /**
     * REQUIRES: toDoList.getAssignmentList() != null
     * MODIFIES: this, toDoList
     * EFFECTS : Creates an assignment in the to do list, and updates the table in the GUI
     */
    private void createAssignment(ToDoList toDoList) {
        AssignmentList assignmentList = toDoList.getAssignmentList();
        Assignment assignment = new Assignment("Untitled Assignment");
        this.constructTask(assignment);
        assignmentList.add(assignment);
    }

    /**
     * REQUIRES: toDoList.getAssignmentList() != null
     * MODIFIES: this, toDoList
     * EFFECTS : Creates an examinable in the to do list, and updates the table in the GUI
     */
    private void createExaminable(ToDoList toDoList) {
        ExaminableList assignmentList = toDoList.getExaminableList();
        Examinable examinable = new Examinable("Untitled Assignment");
        this.constructTask(examinable);
        assignmentList.add(examinable);
    }

    /**
     * MODIFIES: task
     * EFFECTS : Adds the data from the gui into the given task
     */
    private void constructTask(Task task) {
        task.setTitle(this.titleField.getText());
        task.setDescription(this.descriptionArea.getText());
        task.setPoints((int) this.pointsSpinner.getValue());
        DateModel dateModel = this.completeByDatePicker.getModel();
        LocalDate date = LocalDate.of(dateModel.getYear(), dateModel.getMonth() + 1, dateModel.getDay());
        task.setCompleteByDate(date);
    }

    /**
     * MODIFIES: gui
     * EFFECTS : Updates the GUI after an element is added
     */
    private void updateGui(GraphicalUserInterface gui) {
        this.gui.refreshTable();
    }

    private void createUIComponents() {
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel();
        spinnerModel.setValue(Task.MIN_POINTS);
        spinnerModel.setMinimum(Task.MIN_POINTS);
        spinnerModel.setMaximum(Task.MAX_POINTS);
        this.pointsSpinner = new JSpinner(spinnerModel);
    }
}
