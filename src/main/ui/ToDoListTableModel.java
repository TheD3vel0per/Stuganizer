package ui;

import model.Task;
import model.TaskPrioritizer;
import model.ToDoList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ToDoListTableModel extends AbstractTableModel {
    private ToDoList toDoList;
    private List<Task> tasksList;

    /**
     * MODIFIES: this
     * EFFECTS : instantiates the table
     */
    public ToDoListTableModel(ToDoList toDoList) {
        this.toDoList = toDoList;
        this.refresh();
    }

    /**
     * MODIFIES: this
     * EFFECTS : Returns the task located at the given row
     */
    public Task getTask(int index) {
        return this.tasksList.get(index - 1);
    }

    /**
     * MODIFIES: this
     * EFFECTS : refreshes the data in the table
     */
    public void refresh() {
        this.tasksList = this.toDoList.getAllTasks();
        this.tasksList.sort(new TaskPrioritizer());
    }

    @Override
    public int getRowCount() {
        this.refresh();
        return this.tasksList.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex == 0) {
            switch (columnIndex) {
                case 0:
                    return "Points";
                case 1:
                    return "Type";
                case 2:
                    return "Title";
            }
        }

        switch (columnIndex) {
            case 0:
                return this.tasksList.get(rowIndex - 1).getPoints();
            case 1:
                return this.tasksList.get(rowIndex - 1).getClass().getName();
            case 2:
                return this.tasksList.get(rowIndex - 1).getTitle();
        }
        return null;
    }
}
