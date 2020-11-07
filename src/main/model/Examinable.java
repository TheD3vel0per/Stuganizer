package model;

import model.exceptions.CannotStageTask;

import java.time.LocalDate;

/**
 * Read `Task` description and familiarize yourself with the concept before you read
 * the description for an `Examinable`.
 *
 * Examinable are various quizes/tests/midterms/exams that a user must complete on a
 * specific date, not before or after.
 *
 * Some examples of Examinables:
 *  - Pre-class Quiz for Biology
 *  - Take-home test for Chemistry
 *  - Midterm for Calculus
 *  - Final Exam on Physics
 *
 * Some examples of what is not an Examinable:
 *  - Renewing my passport before next week
 *  - Finishing the outline for my essay
 *
 * As mentioned in the `Task` description, sometimes users will have tasks that are
 * staged and completed differently. An `Examinable` is a specific kind of task where
 * staging moves from To-Do to Done Instantly, and can only be staged on a the complete
 * by date.
 */
public class Examinable extends Task {

    private boolean hasBeenCompleted;
    public static Examinable NULL_EXAMINABLE = new Examinable("NULL");

    /**
     * REQUIRES: title string length must be larger than 0
     * MODIFIES: this
     * EFFECTS : Instantiates a new Examinable with the given title, the minimum number of points,
     *           an empty description, and a complete by date of today
     */
    public Examinable(String title) {
        super(title);
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
     * MODIFIES: this
     * EFFECTS : If the completeByDate is today, marks the Examinable as completed
     *           else, throws a CannotStageTask exception
     */
    public void markCompleted() throws CannotStageTask {
        if (LocalDate.now().toString().equals(this.getCompleteByDate().toString())) {
            this.hasBeenCompleted = true;
        } else {
            throw new CannotStageTask();
        }
    }

}
