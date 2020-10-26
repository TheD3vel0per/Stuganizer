package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Please make sure you've familiarized yourself with the `Errand` class
 * before you start reading this!
 *
 * The `ErrandList` is simply a standard list of errands. There are some
 * difference in how this list works compared to other lists.
 *
 *  1. Indexes are occupied once, and not reused during the lifespan
 *     of an instance of an `ErrandList`
 *  2. The removal of an Errand at an index will shrink the size of the list
 *     by one, but the maximum index will still be the same.
 *
 * Example:
 *
 * Given the list of errands `list` with errands, A, B, and C
 *
 * ErrandList list:
 * +-------+--------+
 * | index | errand |
 * +-------+--------+
 * |   0   |   A    |
 * |   1   |   B    |
 * |   2   |   C    |
 * +-------+--------+
 *
 * If we `list.remove(1);` and `list.remove(2);` the errand at index 1:
 * +-------+--------+
 * | index | errand |
 * +-------+--------+
 * |   0   |   A    |
 * |   1   |  null  |
 * |   2   |  null  |
 * +-------+--------+
 *
 * Here are the return values for the size and max index functions:
 * `list.size()` ======> 1
 * `list.maxIndex()` ==> 2
 *
 * Values added onto the end have an index increment of 1.
 *
 * Highest priority is determined by the following ordered criteria:
 *  1. How close the complete by date is from the present
 *  2. The points (a larger number has more priority)
 */
public class ErrandList {
    private List<Errand> errands;

    /**
     * MODIFIES: this
     * EFFECTS : Instantiates the Errand list
     */
    public ErrandList() {
        this.errands = new ArrayList<Errand>();
    }

    /**
     * EFFECTS: Returns the quantity of errands in the list
     */
    public int size() {
        int size = 0;
        for (Errand next : this.errands) {
            if (next != Errand.NULL_ERRAND) {
                size++;
            }
        }
        return size;
    }

    /**
     * EFFECTS: Returns the maximum index of the errand list, if there are
     *          no elements in the list, returns -1
     */
    public int maxIndex() {
        return this.errands.size() - 1;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Adds the given errand to the errand list, and returns its index
     */
    public int add(Errand errandToAdd) {
        this.errands.add(errandToAdd);
        return this.size() - 1;
    }

    /**
     * EFFECTS : Returns the errand located at the given index, if none exists, returns null
     */
    public Errand get(int index) {
        if (index > this.maxIndex()) {
            return null;
        }
        return this.errands.get(index);
    }

    /**
     * EFFECTS: Returns the first Errand in the list which has not been completed, with
     *          the nearest completed by date, if one cannot be found, returns null
     */
    public Errand getNext() {
        Errand highestPriority = null;
        for (Errand next : this.errands) {

            // Skip this errand if its null or is completed
            if (next == Errand.NULL_ERRAND || (next != Errand.NULL_ERRAND && next.isComplete())) {
                continue;
            }

            int nextDays = next.daysUntilCompleteByDate();
            int highDays = highestPriority == null
                    ? Integer.MAX_VALUE : highestPriority.daysUntilCompleteByDate();

            // Compare and assign if higher priority
            if (nextDays <= highDays) {
                if (nextDays == highDays && next.getPoints() > highestPriority.getPoints()) {
                    highestPriority = next;
                } else if (nextDays < highDays) {
                    highestPriority = next;
                }
            }

        }
        return highestPriority;
    }

    /**
     * REQUIRES: There must be an errand at the given index
     * MODIFIES: this
     * EFFECTS : Removes the errand at the given index
     */
    public void remove(int index) {
        this.errands.set(index, Errand.NULL_ERRAND);
    }

    /**
     * EFFECTS: Returns true if the list is empty, otherwise false
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }



}
