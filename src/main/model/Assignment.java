package model;

import org.json.JSONObject;

/**
 * Please make sure you've familiarized yourself with the `Assignment`
 * class before you start reading this!
 *
 * The `Assignment` class is a representation of any school assignment that
 * a user would have to complete.
 *
 * The assignment is staged forward or backwards according to their order.
 * All relevant details about the order of assignment stages, can be seen
 * inside of the AssignmentStage
 *
 *
 */
public class Assignment extends Task {

    public static Assignment NULL_ASSIGNMENT = new Assignment("NULL");

    private static final AssignmentStage DEFAULT_STAGE = AssignmentStage.NEED_TO_START;

    /**
     * Describes what stage the assignment is in
     */
    private AssignmentStage stage;

    /**
     * REQUIRES: title string length must be larger than 0
     * MODIFIES: this
     * EFFECTS : Instatiates a new Assignment with the given title, and sets the
     *           assign
     */
    public Assignment(String title) {
        super(title);
        this.stage = Assignment.DEFAULT_STAGE;
    }

    /**
     * REQUIRES: title string length must be larger than 0
     * MODIFIES: this
     * EFFECTS : Instatiates a new Assignment with the given title, and sets the
     *           assign
     */
    public Assignment(String title, AssignmentStage starting) {
        super(title);
        this.stage = starting;
    }

    /**
     * EFFECTS: Returns the current stage of the assignment
     */
    public AssignmentStage getStage() {
        return this.stage;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Moves the assignment to the next stage, see `AssignmentStage`
     *           enum to find out what the staging order is, returns the new stage
     */
    public AssignmentStage stageForward() {
        switch (this.stage) {
            case NEED_TO_START:
                this.stage = AssignmentStage.STARTED;
                break;
            case STARTED:
                this.stage = AssignmentStage.HALFWAY;
                break;
            case HALFWAY:
                this.stage = AssignmentStage.REVIEW;
                break;
            case REVIEW:
                this.stage = AssignmentStage.SUBMISSION;
                break;
            case SUBMISSION:
            case COMPLETE:
                this.stage = AssignmentStage.COMPLETE;
                break;
        }
        return this.stage;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Moves the assignment to the stage before, see `AssignmentStage`
     *           enum to find out what the staging order is, returns the new stage
     */
    public AssignmentStage stageBackward() {
        switch (this.stage) {
            case NEED_TO_START:
            case STARTED:
                this.stage = AssignmentStage.NEED_TO_START;
                break;
            case HALFWAY:
                this.stage = AssignmentStage.STARTED;
                break;
            case REVIEW:
                this.stage = AssignmentStage.HALFWAY;
                break;
            case SUBMISSION:
                this.stage = AssignmentStage.REVIEW;
                break;
            case COMPLETE:
                this.stage = AssignmentStage.SUBMISSION;
                break;
        }
        return this.stage;
    }

    /**
     * EFFECTS: Returns the percentage of task completion as a floating point number
     *          within the domain [0,1]
     */
    @Override()
    public float percentageComplete() {
        float percentageComplete = 0f;
        switch (this.stage) {
            case NEED_TO_START:
                percentageComplete = 0f;
                break;
            case STARTED:
                percentageComplete = 0.1f;
                break;
            case HALFWAY:
                percentageComplete = 0.5f;
                break;
            case REVIEW:
                percentageComplete = 0.9f;
                break;
            case SUBMISSION:
                percentageComplete = 0.95f;
                break;
            case COMPLETE:
                percentageComplete = 1f;
                break;
        }
        return percentageComplete;
    }

    /**
     * EFFECTS: Returns a JSONObject representing the assignment
     */
    @Override()
    public JSONObject toJson() {
        JSONObject jsonObject = super.toJson();
        jsonObject.put("stage", this.getStage().toString());
        return jsonObject;
    }
}
