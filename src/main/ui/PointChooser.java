package ui;

import model.AssignmentList;
import model.ErrandList;
import model.ExaminableList;
import model.ToDoList;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PointChooser extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JSpinner pointsSpinner;

    private ToDoList toDoList;
    private GraphicalUserInterface gui;

    /**
     * MODIFIES: this
     * EFFECTS : Instantiates the point chooser, and prompts the user to select how many
     *           points they'd like to accomplish per day
     */
    public PointChooser(ToDoList toDoList, GraphicalUserInterface gui) {
        this.toDoList = toDoList;
        this.gui = gui;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    /**
     * MODIFIES: this, this.toDoList
     * EFFECTS : Sets the quantity of points per day, when the points button is pressed
     */
    private void onOK() {
        // add your code here
        this.toDoList.setPointsPerDay((int) this.pointsSpinner.getValue());
        this.toDoList.setErrandList(new ErrandList());
        this.toDoList.setAssignmentList(new AssignmentList());
        this.toDoList.setExaminableList(new ExaminableList());
        this.gui.setupGraphicalInterface();
        dispose();
    }

    /**
     * MODIFIES: this
     * EFFECTS : Instantiates the spinner component
     */
    private void createUIComponents() {
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel();
        spinnerModel.setValue(1);
        spinnerModel.setMinimum(1);
        this.pointsSpinner = new JSpinner(spinnerModel);
    }
}
