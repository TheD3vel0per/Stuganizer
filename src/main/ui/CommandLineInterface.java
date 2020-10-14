package ui;

import model.Errand;
import model.ErrandList;
import model.Task;
import model.ToDoList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {

    private Scanner scanner;
    private ToDoList toDoList;
    private ErrandList errandList;
    private BufferedReader obj;

    // MODIFIES: this
    // EFFECTS : Instantiates the command line interface
    public CommandLineInterface() {
        this.obj = new BufferedReader(new InputStreamReader(System.in));
        this.scanner = new Scanner(System.in);
        this.errandList = new ErrandList();
        this.toDoList = new ToDoList(this.askPointsPerDay());
        this.toDoList.setErrandList(this.errandList);
        this.clearScreen();
        this.chooseAction();
    }

    // EFFECTS: Clears the screen
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // EFFECTS: Show the title to the user
    private void showTitle() {
        System.out.println("                                                                        ");
        System.out.println(" ,---.   ,--.                                 ,--.                      ");
        System.out.println("'   .-',-'  '-.,--.,--. ,---.  ,--,--.,--,--, `--',-----. ,---. ,--.--. ");
        System.out.println("`.  `-.'-.  .-'|  ||  || .-. |' ,-.  ||      \\,--.`-.  / | .-. :|  .--' ");
        System.out.println(".-'    | |  |  '  ''  '' '-' '\\ '-'  ||  ||  ||  | /  `-.\\   --.|  |    ");
        System.out.println("`-----'  `--'   `----' .`-  /  `--`--'`--''--'`--'`-----' `----'`--'    ");
        System.out.println("                       `---'                                            ");
    }

    // EFFECTS: Shows the welcome message to the user
    private void showWelcomeMessage() {
        this.showTitle();
        System.out.println("Made with <3 by Devam Sisodraker a CS+Math Student @ UBC");
        System.out.println("Help students organize what you have to do with errands, assignments, exams and more!");
        System.out.println("Copyright (C) Devam Sisodraker " + LocalDate.now().getYear());
    }

    // EFFECTS: Asks the user how many points they'd like to accomplish per day
    private int askPointsPerDay() {
        this.showWelcomeMessage();
        int pointsPerDay = -1;
        do {
            System.out.println("Please enter how many points you'd like to accomplish per day:");
            int nextInt = this.scanner.nextInt();
            if (nextInt > 0) {
                pointsPerDay = nextInt;
                break;
            }
            System.out.println("That wasn't a valid number, please a positive integer!\n");
        } while (true);
        System.out.println("Thanks! We'll help you make sure you accomplish " + pointsPerDay + " points per day");
        return pointsPerDay;
    }

    // EFFECTS: Refreshes the screen with and adds the logo
    private void refreshConsole() {
        this.clearScreen();
        this.showTitle();
        System.out.println("");
    }

    // REQUIRES: min and max must be greater than 0, max must be greater than min
    // EFFECTS : Returns an integer from the user within the given domain: [min, max]
    private int getIntegerFromUser(int min, int max) {
        int selectedInt = 0;
        while (!(min <= selectedInt && selectedInt <= max)) {
            selectedInt = this.scanner.nextInt();
            if (min <= selectedInt && selectedInt <= max) {
                break;
            } else {
                System.out.print("Please choose an integer from " + min + " to " + max + " : ");
            }
        }
        return selectedInt;
    }

    // EFFECTS: Lists all the actions which can be performed, and prompts a user to perform one,
    private int promptActions() {
        System.out.println("\n\n\nActions");
        System.out.println("--------------------------------------");
        System.out.println("(1) View tasks for today");
        System.out.println("(2) Add an errand to my to do list");
        System.out.println("(3) Mark an errand as completed");
        return this.getIntegerFromUser(1, 3);
    }

    // EFFECTS: Prompts the user to choose an action to perform
    private void chooseAction() {
        while (true) {
            // 10this.refreshConsole();
            switch (this.promptActions()) {
                case 1:
                    this.viewTasksForToday();
                    break;
                case 2:
                    this.addErrand();
                    break;
                case 3:
                    this.markErrandComplete();
                    break;
            }
        }
    }

    // EFFECTS: Prints the task details out to the console, does not print the title
    private void printTaskDetails(Task task) {
        System.out.println("\tDescription: " + task.getDescription());
        System.out.println("\tPoints: " + task.getPoints());
        System.out.println("\tComplete by Date: " + task.getCompleteByDate());
        System.out.println("\tCompleted: " + task.isComplete());
        System.out.print("\n");
    }

    // EFFECTS: Prints out all the tasks that a user has to do today, and returns all the tasks for today
    private List<Task> viewTasksForToday() {
        List<Task> tasksForToday = this.toDoList.getTasksForToday();
        for (int i = 0; i < tasksForToday.size(); i++) {
            Task task = tasksForToday.get(i);
            System.out.println("(" + (i + 1) + ")\t" + task.getTitle());
            this.printTaskDetails(task);
        }
        return tasksForToday;
    }

    // MODIFIES: this
    // EFFECTS : Adds a new errand to the to do list
    private void addErrand() {
        try {
            System.out.print("\nPlease enter the following information about the errand:\n");
            System.out.print("Title: ");
            String title = this.obj.readLine();
            Errand errand = new Errand(title);

            System.out.print("Description: ");
            String description = this.obj.readLine();
            errand.setDescription(description);

            System.out.print("Points: ");
            int points = this.getIntegerFromUser(Task.MIN_POINTS, Task.MAX_POINTS);
            errand.setPoints(points);

            System.out.print("Complete By Date (YYYY-MM-DD): ");
            LocalDate completeBy = LocalDate.parse(this.obj.readLine());
            errand.setCompleteByDate(completeBy);

            this.errandList.add(errand);
        } catch (IOException error) {
            System.out.println("There was an error creating the errand.");
        }
    }

    // MODIFIES: this
    // EFFECTS : Asks the user which errand to mark as complete, then marks that errand as complete
    private void markErrandComplete() {
        List<Task> tasks = this.viewTasksForToday();
        System.out.print("Which errand would you like to mark as complete?: ");
        int indexToMark = this.getIntegerFromUser(1, tasks.size()) - 1;
        Errand taskToMark = (Errand) tasks.get(indexToMark);

        taskToMark.markCompleted();
        this.toDoList.markTaskCompleted(taskToMark);
    }

}
