package model;

import org.json.JSONObject;
import persistence.JsonifiableObject;

import java.time.LocalDate;
import java.util.ArrayList;
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
 * Another note when generating a list for the day, the list will overflow the
 * amount of designated points for a day. Example:
 *
 *   Let the points per day be equal to 10
 *
 *   Given Task A => 3 points
 *         Task B => 4 points
 *         Task C => 5 points
 *         Task D => 5 points
 *         Task E => 2 points
 *
 *   Assuming tasks are prioritized as A, B, C, D, E
 *
 *   The list generated will be A, B, C, will have a total of:
 *     3 + 4 + 5 = 12 points on the to do list
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
public class ToDoList implements JsonifiableObject {

    private int pointsPerDay;
    private List<Task> tasksCompleted;
    private LocalDate lastDate;

    private ErrandList errandList;
    private AssignmentList assignmentList;
    private ExaminableList examinableList;

    /**
     * REQUIRES: Integer supplied must in the domain of [Task.MAX_POINTS, Integer.MAX_VALUE]
     * MODIFIES: this
     * EFFECTS : Instantiates a new to do list with the goal of the given integer of points per day
     */
    public ToDoList(int pointsPerDay) {
        this.pointsPerDay = pointsPerDay;
        this.tasksCompleted = new ArrayList<>();
        this.lastDate = LocalDate.now();
    }

    /**
     * EFFECTS: Returns the amount of points that the user wants to accomplish per day
     */
    public int getPointsPerDay() {
        return this.pointsPerDay;
    }

    /**
     * EFFECTS: Returns the amount of points awarded for the last day
     */
    public int getPointsAwarded() {
        this.resetForNextDay();
        int pointsAwarded = 0;
        for (Task task : this.tasksCompleted) {
            if (task.isComplete()) {
                pointsAwarded += task.getPoints();
            }
        }
        return pointsAwarded;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Attaches the given errand list to the to do list
     */
    public void setErrandList(ErrandList errandList) {
        this.errandList = errandList;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Attaches the given errand list to the to do list
     */
    public void setAssignmentList(AssignmentList assignmentList) {
        this.assignmentList = assignmentList;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Attaches the given examinable list to the to do list
     */
    public void setExaminableList(ExaminableList examinableList) {
        this.examinableList = examinableList;
    }

    /**
     * EFFECTS: Gets the errand list associated with the to do list
     */
    public ErrandList getErrandList() {
        return this.errandList;
    }

    /**
     * EFFECTS: Gets the assignment list associated with the to do list
     */
    public AssignmentList getAssignmentList() {
        return this.assignmentList;
    }

    /**
     * EFFECTS: Gets the assignment list associated with the to do list
     */
    public ExaminableList getExaminableList() {
        return this.examinableList;
    }

    /**
     * EFFECTS: Returns all the tasks which have been completed on the day
     */
    public List<Task> getTasksCompletedToday() {
        return this.tasksCompleted;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Returns all tasks scheduled for today, including completed ones, will overflow
     *           if tasks
     */
    public List<Task> getTasksForToday() {
        this.resetForNextDay();

        List<Task> tasksToday = new ArrayList<>();
        List<Task> allTasks = this.getAllTasks();
        allTasks.sort(new TaskPrioritizer());

        int collectablePoints = 0;
        for (Task task : allTasks) {
            if (collectablePoints >= this.pointsPerDay) {
                break;
            }

            if (!(task.getClass() == Examinable.class && task.getCompleteByDate().isEqual(LocalDate.now()))) {
                tasksToday.add(task);
                collectablePoints += task.getPoints();
            }
        }

        return tasksToday;
    }

    /**
     * EFFECTS: Returns all the tasks in the to do list in no particular order, including completed ones
     */
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i <= this.errandList.maxIndex(); i++) {
            Errand errand = this.errandList.get(i);
            if (errand != Errand.NULL_ERRAND) {
                tasks.add(errand);
            }
        }

        for (int i = 0; i <= this.assignmentList.maxIndex(); i++) {
            Assignment assignment = this.assignmentList.get(i);
            if (assignment != Assignment.NULL_ASSIGNMENT) {
                tasks.add(assignment);
            }
        }

        for (int i = 0; i <= this.examinableList.maxIndex(); i++) {
            Examinable examinable = this.examinableList.get(i);
            if (examinable != Examinable.NULL_EXAMINABLE) {
                tasks.add(examinable);
            }
        }
        return tasks;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Marks the given task as completed
     */
    public boolean markTaskCompleted(Task taskToBeMarked) {
        if (taskToBeMarked.isComplete()) {
            this.tasksCompleted.add(taskToBeMarked);
            return true;
        } else {
            return false;
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS : Resets class for the next day, if it needs to be
     */
    private void resetForNextDay() {
        if (lastDate == null || !lastDate.toString().equals(LocalDate.now().toString())) {
            // Reset points awarded for the day
            this.lastDate = LocalDate.now();
            this.tasksCompleted = new ArrayList<>();
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS : This method is only to be used when conducting tests, since
     *           a test cannot wait for the next day to occur (24 hours max)
     */
    public void nullifyDate() {
        this.lastDate = null;
    }

    /**
     * EFFECTS: Returns the to do list represented as a JSONObject
     */
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pointsPerDay",this.pointsPerDay);
        jsonObject.put("errandList", this.errandList.toJson());
        jsonObject.put("assignmentList", this.assignmentList.toJson());
        jsonObject.put("examinableList", this.examinableList.toJson());
        return jsonObject;
    }
}
