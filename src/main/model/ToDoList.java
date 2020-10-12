package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 * A core functionality of the application is knowing what you're going to do
 * today, and how much you're going to do today. What you'll accomplish is
 * compiled from various lists and generated in the to do list class.
 *
 * This class is combines all forms of tasks, orders them by priority,
 * and is designed to help the user determine what they'd like to accomplish
 * for the day.
 *
 * The to do list organizes the tasks the user must complete, and takes in
 * various forms of tasks. It also rewards points based on the completion of
 * those tasks.
 *
 * When generating a list of tasks to accomplish for today, the to do list
 * should know where to source tasks from. Since there are many different types
 * of tasks, and tasks cannot be instantiated, the way you add these tasks is by
 * adding them to lists, and associating the lists with the to do list.
 *
 * Each to do list will only have one tasks list for each task type. For example,
 * A to do lists will only have one associated `ErrandList`, but can have another
 * type of list for tasks for assignments, and exams. They will have to be set up
 * as sources for the `ToDoList`, using the corresponding functions.
 *
 * Although there is only one type of list to source tasks from in this version
 * of the application, there will be more as the application expands. That is why
 * this class is being implemented now, instead of just fetching from the `ErrandList`
 * directly.
 */
public class ToDoList {

    private int pointsPerDay;
    private List<Task> tasksCompleted;
    private LocalDate lastDate;

    private ErrandList errandList;

    // REQUIRES: Integer supplied must in the domain of [Task.MAX_POINTS, Integer.MAX_VALUE]
    // MODIFIES: this
    // EFFECTS : Instantiates a new to do list with the goal of the given integer of points per day
    public ToDoList(int pointsPerDay) {
        this.pointsPerDay = pointsPerDay;
        this.tasksCompleted = new ArrayList<>();
    }

    // EFFECTS: Returns the amount of points awarded for the last day
    public int getPointsAwarded() {
        // TODO: Implementation for the ToDoList.getTasksForToday method
        this.resetForNextDay();
        return 0;
    }

    // MODIFIES: this
    // EFFECTS : Attaches the given errand list to the
    public void setErrandList(ErrandList errandList) {
        // TODO: Implementation for the ToDoList.setErrandList method
    }

    // EFFECTS: Gets the errand list associated with the to do list
    public ErrandList getErrandList() {
        // TODO: Implementation for the ToDoList.getErrandList method
        return null;
    }

    public List<Task> getTasksCompletedToday() {
        // TODO: Implementation for the ToDoList.getTasksForToday method
        return null;
    }

    // MODIFIES: this
    // EFFECTS : Returns all tasks scheduled for today, including completed ones
    public List<Task> getTasksForToday() {
        // TODO: Implementation for the ToDoList.getTasksForToday method
        this.resetForNextDay();

        // Generate list
        return null;
    }

    // EFFECTS: Returns all the tasks in the to do list in no particular order, including completed ones
    public List<Task> getAllTasks() {
        // TODO: Implementation for the ToDoList.getAllTasks method
        return null;
    }

    // MODIFIES: this
    // EFFECTS : Marks the given task as completed
    public boolean markTaskCompleted(Task taskToBeMarked) {
        return false;
    }

    // MODIFIES: this
    // EFFECTS : Resets class for the next day, if it needs to be
    private void resetForNextDay() {
        if (lastDate == null || lastDate.toString() != LocalDate.now().toString()) {
            // Reset points awarded for the day
        }
    }


}
