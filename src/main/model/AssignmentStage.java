package model;

/**
 * Please read the document on the Assignment class before you read this!
 *
 * These are the various stages for an assignment, you'll find comments
 * for each of the enum entries marking information such as:
 *  - Percentage Complete
 *  - Description of what stage the assignment should be at]
 *
 *  The order of assignment staging is as follows:
 *   - NEED_TO_START
 *   - STARTED
 *   - HALFWAY
 *   - REVIEW
 *   - SUBMISSION
 *   - COMPLETE
 */
public enum AssignmentStage {

    /**
     * Percentage Complete: 0%
     * At this stage, the assignment has not been started
     */
    NEED_TO_START,

    /**
     * Percentage Complete: 10%
     * At this stage, the assignment has been started
     */
    STARTED,

    /**
     * Percentage Complete: 50%
     * At this stage, the assignment has been at least half completed
     */
    HALFWAY,

    /**
     * Percentage Complete: 90%
     * At this stage there is only a little bit of review remaining for the assignment
     */
    REVIEW,

    /**
     * Percentage Complete: 95%
     * At this stage, the assignment only needs to be handed in
     */
    SUBMISSION,

    /**
     * Percentage Complete: 100%
     * At this stage, the assignment is complete, nothing more needs to be done
     */
    COMPLETE

}
