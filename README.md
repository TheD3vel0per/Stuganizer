# Stuganizer
Help students organize what you have to do with errands, assignments, exams and more!

### What will the application do?
 * Allow you to add different types of tasks (errands, assignments, test)
 * Allows you to stage different tasks that you have 

### Who will use it?
Hopefully University students, especially first years. I found that my schedule was very busy and my favourite way to
organize everything was to use a Kanban organizational system. I know I definitely will use it.

### Why is this project of interest to you?
As much I enjoy using Trello to organize myself, I feel like there are certain features lacking, particularly when
dealing with different courses and errands. Something like an essay may need several stages like to do, in progress, 
and review. Whereas something like buying groceries will only have two states: to do and done. Exams also cannot be
completed in advance, they can only be done on a specific date.

## User Stories
As a user I want to be able to ...  
 * View what I have to do for the day (Completed in Phase 1!)
 * Set how many points I'd like to accomplish per day (Completed in Phase 1!)
 * Assign points (representing difficulty) for each errand/assignment/quiz, so you can have a consistent workload
   (Completed in Phase 1!)
 * Add an errand to my errands list (Completed in Phase 1!)
 * Mark a task completed on my errands list (Completed in Phase 1!)
 * Save my tasks and other configuration to a file (Complete in Phase 2!)
 * Reload my tasks and other configuration from a file (Complete in Phase 2!)
 * Add an assignment to my board (Complete in Phase 2!)
 * Stage the progress my assignments (to do, doing, under review, to submit, complete etc.) on the Kanban board 
 (Complete in Phase 2!)
 * Add quizzes/tests/exams for a particular day (Complete in Phase 2)
 * View what quizzes/tests/exams I have to do for today (Complete in Phase 2)
 * Not allowing tasks to be stage forward if they don't fit the circumstances. (Completed before Phase 4).
 
## Phase 4: Task 3
I would probably create an abstract class `TaskList` and then use that as a basis for `ErrandList`, `AssignmentList`,
and `ExaminableList` or just abandon them and insert them inside the to-do list. I originally planned to be able to
view all of them separately and then all together. But I didn't have enough time to do it, so doing just a ToDoList 
would probably be enough, however having it separated gives me flexibility if I work on this project after the course is
finished.