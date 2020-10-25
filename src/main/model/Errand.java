package model;

import java.time.LocalDate;

/**
 * Read `Task` description and familiarize yourself with the concept before you read
 * the description for an `Errand`.
 *
 * Errands are small things the user has to do, some examples:
 *  - I have to renew my passport before next week
 *  - I need to buy more groceries before the end of the month
 *  - I need to take my dog on a walk today
 *
 * Some examples of what an errand isn't:
 *  - I need to finish half of my webwork
 *  - I need to review my essay before I hand it in
 *  - I have to finish a Canvas quiz today
 *
 * As mentioned in the `Task` description, sometimes users will have tasks that are
 * staged and completed differently. An `Errand` is a specific kind of task where
 * staging moves from To-Do to Done Instantly.
 */
public class Errand extends Task {

    private boolean hasBeenCompleted;
    public static Errand NULL_ERRAND = new Errand("NULL");

    /**
     * EFFECTS: Creates a new untitled errand which has not been completed
     */
    public Errand() {
        super("Untitled Errand");
        this.hasBeenCompleted = false;
    }

    /**
     * REQUIRES: title string length must be larger than 0
     * MODIFIES: this
     * EFFECTS : Creates a new errand with the given title which has not been completed
     */
    public Errand(String title) {
        super(title);
        this.hasBeenCompleted = false;
    }

    /**
     * REQUIRES: title string length must be larger than 0
     * MODIFIES: this
     * EFFECTS : Creates a new errand with the given title, and complete by date,
     *           which has not been completed
     */
    public Errand(String title, LocalDate completeByDate) {
        super(title, "", completeByDate);
        this.hasBeenCompleted = false;
    }

    /**
     * EFFECTS: Returns the percentage of task completion as a floating point number
     *          within the domain [0,1]
     */
    @Override()
    public float percentageComplete() {
        if (this.hasBeenCompleted) {
            return 1f;
        }
        return 0f;
    }

    /**
     * EFFECTS: Marks the Errand as completed
     */
    public void markCompleted() {
        this.hasBeenCompleted = true;
    }


}
