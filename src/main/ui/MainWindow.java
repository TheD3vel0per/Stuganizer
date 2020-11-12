package ui;

import model.Errand;
import model.Task;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JTable dataTable;
    private JLabel titleLabel;
    private JLabel descriptionLabel;
    private JLabel pointsLabel;
    private JLabel percentageLabel;
    private JLabel completeByLabel;
    private JLabel stageLabel;
    private JButton submitButton;
    private JButton stageButton;
    private JLabel typeLabel;

    private GraphicalUserInterface gui;

    public MainWindow(GraphicalUserInterface gui) {
        super("Stuganizer");
        this.gui = gui;
        this.setContentPane(this.mainPanel);
    }

    /**
     * EFFECTS: Show the given task in the UI
     */
    public void showTaskInPanel(Task task) {

    }

    /**
     * EFFECTS: Displays the given Errand in the UI
     */
    private void showErrandInPanel(Errand errand) {

    }

    /**
     * EFFECTS: Displays the given Errand in the UI
     */
    private void showAssignmentInPanel(Errand errand) {

    }

    /**
     * EFFECTS: Displays the given Errand in the UI
     */
    private void showExaminableInPanel(Errand errand) {

    }

    /**
     * MODIFIES: this
     * EFFECTS : Manually instantiates the data table in the UI
     */
    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.dataTable = new JTable(this.gui.getTableModel());
        dataTable.addMouseListener(new TableMouseEvent(gui, dataTable));
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
            this.gui.setSelectedTask(this.gui.getTableModel().getTask(this.dataTable.getSelectedRow()));
        }
    }
}
