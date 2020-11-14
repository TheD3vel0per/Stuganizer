package ui;

import model.Assignment;
import model.Errand;
import model.Examinable;
import model.Task;
import model.exceptions.CannotStageTask;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JTable dataTable;
    private JLabel typeLabel;
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel pointsLabel;
    private JLabel completeByLabel;
    private JLabel stageLabel;
    private JButton stageButton;

    private GraphicalUserInterface gui;

    public MainWindow(GraphicalUserInterface gui) {
        super("Stuganizer");
        this.gui = gui;
        this.setContentPane(this.mainPanel);
        stageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Task task = gui.getSelectedTask();
                switch (task.getClass()) {
                    case Errand.class:
                        ((Errand) task).markCompleted();
                        break;
                    case Assignment.class:
                        ((Assignment) task).stageForward();
                        break;
                    case Examinable.class:
                        try {
                            ((Examinable) task).markCompleted();
                        } catch (CannotStageTask cannotStageTask) {
                            // TODO show dialog to stage on another day
                        }
                        break;
                }
                super.mouseClicked(e);
            }
        });
    }

    /**
     * MODIFIES: this
     * EFFECTS : Manually instantiates the data table in the UI
     */
    private void createUIComponents() {
        this.dataTable = new JTable(this.gui.getTableModel());
        dataTable.addMouseListener(new TableMouseEvent(gui, dataTable));
    }

    /**
     * MODIFIES: this
     * EFFECTS : Show the given task in the UI
     */
    public void showTaskInPanel(Task task) {
        if (task != null) {
            this.titleLabel.setText("Title: " + task.getTitle());
            this.descriptionLabel.setText("Description: " + task.getDescription());
            this.pointsLabel.setText("Points: " + Integer.toString(task.getPoints()));
            this.completeByLabel.setText("Complete By Date: " + task.getCompleteByDate().toString());
            if (Errand.class.equals(task.getClass())) {
                this.showErrandInPanel((Errand) task);
            } else if (Assignment.class.equals(task.getClass())) {
                this.showAssignmentInPanel((Assignment) task);
            } else if (Examinable.class.equals(task.getClass())) {
                this.showExaminableInPanel((Examinable) task);
            }
        } else {
            this.showBlankInPanel();
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS : Removes all the content from the labels, and just puts the default there
     */
    private void showBlankInPanel() {
        this.typeLabel.setText("Type: ");
        this.titleLabel.setText("Title: ");
        this.descriptionLabel.setText("Description: ");
        this.pointsLabel.setText("Points: ");
        this.completeByLabel.setText("Complete By Date: ");
        this.stageLabel.setText("Current Stage: ");
    }

    /**
     * MODIFIES: this
     * EFFECTS : Displays the given Errand in the UI
     */
    private void showErrandInPanel(Errand task) {
        this.typeLabel.setText("Type: Errand");
        if (task.isComplete()) {
            this.stageLabel.setText("Current Stage: Complete");
            return;
        }
        this.stageLabel.setText("Current Stage: Incomplete");
    }

    /**
     * MODIFIES: this
     * EFFECTS : Displays the given Errand in the UI
     */
    private void showAssignmentInPanel(Assignment task) {
        this.typeLabel.setText("Type: Assignment");
        this.stageLabel.setText("Current Stage: " + task.getStage().toString());
    }

    /**
     * MODIFIES: this
     * EFFECTS : Displays the given Errand in the UI
     */
    private void showExaminableInPanel(Examinable task) {
        this.typeLabel.setText("Type: Examinable");
        if (task.isComplete()) {
            this.stageLabel.setText("Current Stage: Complete");
            return;
        }
        this.stageLabel.setText("Current Stage: Incomplete");
    }

    private class TableMouseEvent extends MouseAdapter {

        private GraphicalUserInterface gui;
        private JTable dataTable;

        /**
         * MODIFIES: this
         * EFFECTS : instantiates the TableMouseEvent
         */
        public TableMouseEvent(GraphicalUserInterface gui, JTable dataTable) {
            super();
            this.gui = gui;
            this.dataTable = dataTable;
        }

        /**
         * MODIFIES: this
         * EFFECTS : Event for when the mouse button is clicked
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            int selectedRow = this.dataTable.getSelectedRow();
            Task selectedTask;
            if (selectedRow == 0) {
                selectedTask = null;
            } else {
                selectedTask = this.gui.getTableModel().getTask(selectedRow);
            }
            this.gui.setSelectedTask(selectedTask);
        }
    }
}
