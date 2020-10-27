package model;

import org.json.JSONObject;
import persistence.JsonifiableObject;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * A task is a core part of this application. It defines something that a user has
 * to do. It's an abstract class because there may be different kinds of tasks, and
 * this is its base form.
 *
 * Each task awards a certain quantity of points, which may vary across various tasks,
 * with more difficult tasks holding more points, and less intense tasks rewarding
 * less points. The amount of points rewarded by a task is an integer in the
 * domain of [MIN_POINTS,MIN_POINTS].
 *
 * Each task also has a title and description. The title of a task defines in short
 * words what the task is, and the description holds more information about the
 * task, if the user would like to add more information.
 *
 * Finally, tasks have a complete by date. Which specify the date the the task
 * should be completed on or by.
 *
 * Staging tasks works different depending on the kind of task, so no information
 * about whether a task is complete or not is defined immediately. Some tasks may
 * go instantly from to-do to done, while other might require reviewing, submitting,
 * and other factor to be considered officially complete.
 *
 * Because of this, there is one method which must be overriden by each class
 * that inherits `Task`. The method `percentageComplete` must return a floating point
 * value which represents the percentage completed of a task.
 *
 * Since this is an abstract class, this class will be thoroughly tested in the
 * `ErrandTest` tests.
 */
public abstract class Task implements JsonifiableObject {

    public static int MIN_POINTS = 1;
    public static int MAX_POINTS = 5;

    /**
     * Specifies the quantity of point accomplished when doing this task
     * score > 0
     */
    private int points;

    /**
     * Title of a task, length must be >0
     */
    private String title;

    /**
     * Description of the task
     */
    private String description;

    /**
     * Date the task should be completed on or by
     */
    private LocalDate completeByDate;

    /**
     * REQUIRES: title string length must be larger than 0
     * MODIFIES: this
     * EFFECTS : Instantiates a new Task with the given title, the minimum number of points,
     *           an empty description, and a complete by date of today
     */
    public Task(String title) {
        this.points = Task.MIN_POINTS;
        this.title = title;
        this.description = "";
        this.completeByDate = LocalDate.now();
    }

    /**
     * REQUIRES: title string length must be larger than 0
     * MODIFIES: this
     * EFFECTS : Instantiates a new Task with the given title, the minimum number of points,
     *          the given description, and a complete by date of today
     */
    public Task(String title, String description, LocalDate completeByDate) {
        this.points = Task.MIN_POINTS;
        this.title = title;
        this.description = description;
        this.completeByDate = completeByDate;
    }

    /**
     * EFFECTS: Returns the amount of points this task awards
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * EFFECTS: Returns the title of the task as a string
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * EFFECTS: Returns the description of the task as a string
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * EFFECTS: Return the complete by date
     */
    public LocalDate getCompleteByDate() {
        return this.completeByDate;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Sets the amount of points this task gets upon completion if points
     *           is within the domain [MIN_POINTS, MAX_POINTS] and returns true,
     *           otherwise returns false
     */
    public boolean setPoints(int points) {
        if ((MIN_POINTS <= points) && (points <= MAX_POINTS)) {
            this.points = points;
            return true;
        }
        return false;
    }

    /**
     * REQUIRES: title string length must be larger than 0
     * MODIFIES: this
     * EFFECTS : Sets the title of the task
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Sets a new description for the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * REQUIRES: completeByDate must be the current date, or a date in the future
     * MODIFIES: this
     * EFFECTS : Sets the complete by date for the task
     */
    public void setCompleteByDate(LocalDate completeByDate) {
        this.completeByDate = completeByDate;
    }

    /**
     * EFFECTS: Returns how many days there are until the complete by day
     */
    @SuppressWarnings("checkstyle:MethodParamPad")
    public int daysUntilCompleteByDate() {
        LocalDate now = LocalDate.now();

        return (int) ChronoUnit.DAYS.between(this.completeByDate, now);
    }

    /**
     * EFFECTS: Returns whether or not the task is complete
     */
    public boolean isComplete() {
        return this.percentageComplete() == 1;
    }

    /**
     * EFFECTS: Returns the percentage of task completion as a floating point number
     *          within the domain [0,1]
     */
    public abstract float percentageComplete();

    /**
     * EFFECTS: Returns a JSONObject representing the task
     */
    @Override()
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", this.getTitle());
        jsonObject.put("description", this.getDescription());
        jsonObject.put("points", this.getPoints());
        jsonObject.put("completeByDate", this.getCompleteByDate().toString());
        jsonObject.put("completed", this.isComplete());
        return jsonObject;
    }

}
