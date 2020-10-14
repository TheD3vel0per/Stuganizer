package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * Since Java already gives us mechanism to sort things automatically,
 * we write comparators so that Java knows how to order complex classes.
 *
 * This comparator is for tasks, and it helps sort by the priority of each
 * tasks. Things which determine the priority of the task are listed below,
 * in the order of their priority (first being highest):
 *  1. Is not null
 *  2. If a task has a closer due dates
 *  3. How many points a task awards (more points: higher priority)
 *  4. The type of task (first being higher priority)
 *    a. Errand
 *
 * For the fourth point above, there is only one type of task in the
 * application right now, a different priority order will be set when more
 * tasks are added.
 */
public class TaskPrioritizer implements Comparator<Task> {


    // EFFECTS: Returns  1 if task a goes before task b
    //          Returns -1 if task b goes before task a
    //          Returns  0 if task a and task b can occupy the same position
    @Override
    public int compare(Task a, Task b) {
        if (a == b) {
            return 0;
        }

        // Which ones has the closer due day
        long completeDaysA = Math.abs(ChronoUnit.DAYS.between(a.getCompleteByDate(), LocalDate.now()));
        long completeDaysB = Math.abs(ChronoUnit.DAYS.between(b.getCompleteByDate(), LocalDate.now()));
        if (completeDaysA < completeDaysB) {
            return -1;
        }
        if (completeDaysA > completeDaysB) {
            return 1;
        }

        // Which one awards more points
        if (a.getPoints() > b.getPoints()) {
            return -1;
        }
        if (a.getPoints() < b.getPoints()) {
            return 1;
        }

        // Since the only type of task that exists are errands:
        return 0;
    }
}
