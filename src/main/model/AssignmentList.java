package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Please make sure you've familiarized yourself with the `Assignment` class
 * before you start reading this!
 *
 * The `AssignmentList` is a list of Assignments. But like the `AssignmentList` there
 * are some differences in how this list works compared to other lists.
 *
 * Example:
 *
 * Given the list of assignments `list` with assignments, A, B, and C
 *
 * AssignmentList list:
 * +-------+------------+
 * | index | assignment |
 * +-------+------------+
 * |   0   |      A     |
 * |   1   |      B     |
 * |   2   |      C     |
 * +-------+------------+
 *
 * If we `list.remove(1);` and `list.remove(2);` the assignment at index 1:
 * +-------+------------+
 * | index | assignment |
 * +-------+------------+
 * |   0   |    null    |
 * |   1   |    null    |
 * |   2   |    null    |
 * +-------+------------+
 *
 * Here are the return values for the size and max index functions:
 * `list.size()` ======> 1
 * `list.maxIndex()` ==> 2
 *
 * Values added onto the end have an index increment of 1.
 */
public class AssignmentList {
    private List<Assignment> assignments;

    /**
     * MODIFIES: this
     * EFFECTS : Instantiates the Assignment list
     */
    public AssignmentList() {
        this.assignments = new ArrayList<Assignment>();
    }

    /**
     * EFFECTS: Returns the quantity of assignments in the list
     */
    public int size() {
        int size = 0;
        for (Assignment next : this.assignments) {
            if (next != Assignment.NULL_ASSIGNMENT) {
                size++;
            }
        }
        return size;
    }

    /**
     * EFFECTS: Returns the maximum index of the assignment list, if there are
     *          no elements in the list, returns -1
     */
    public int maxIndex() {
        return this.assignments.size() - 1;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Adds the given assignment to the assignment list, and returns its index
     */
    public int add(Assignment assignmentToAdd) {
        this.assignments.add(assignmentToAdd);
        return this.size() - 1;
    }

    /**
     * EFFECTS : Returns the assignment located at the given index, if none exists, returns null
     */
    public Assignment get(int index) {
        if (index > this.maxIndex()) {
            return null;
        }
        return this.assignments.get(index);
    }

    /**
     * REQUIRES: There must be an assignment at the given index
     * MODIFIES: this
     * EFFECTS : Removes the assignment at the given index
     */
    public void remove(int index) {
        this.assignments.set(index, Assignment.NULL_ASSIGNMENT);
    }

    /**
     * EFFECTS: Returns true if the list is empty, otherwise false
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }


}
