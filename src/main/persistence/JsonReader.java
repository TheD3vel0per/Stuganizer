package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * This code was sourced from the demo code located at:
 * https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 *
 * It was slightly modified to account for the different data structures
 * in this application.
 *
 * This class given a JSON file, will put all the data into a to do class
 * and will return the class for furthur use by the program.
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        int pointsPerDay = jsonObject.getInt("pointsPerDay");

        ToDoList toDoList = new ToDoList(pointsPerDay);
        toDoList.setAssignmentList(extractAssignments(jsonObject));
        toDoList.setErrandList(extractErrands(jsonObject));
        return toDoList;
    }


    // EFFECTS : parses assignments from JSON object and returns the assignment list
    private AssignmentList extractAssignments(JSONObject jsonObject) {
        AssignmentList assignmentList = new AssignmentList();
        JSONArray jsonArray = jsonObject.getJSONArray("assignmentList");
        for (Object json : jsonArray) {
            JSONObject nextAssignment = (JSONObject) json;
            assignmentList.add(extractAssignment(nextAssignment));
        }
        return assignmentList;
    }

    // EFFECTS: parses assignment from JSON object and returns it
    private Assignment extractAssignment(JSONObject jsonObject) {
        Assignment assignment = new Assignment(
                jsonObject.getString("title"),
                AssignmentStage.fromString(jsonObject.getString("stage"))
        );
        assignment.setDescription(jsonObject.getString("description"));
        assignment.setPoints(jsonObject.getInt("points"));
        assignment.setCompleteByDate(LocalDate.parse(jsonObject.getString("completeByDate")));
        return assignment;
    }

    // EFFECTS : parses errands from JSON object and returns the errand list
    private ErrandList extractErrands(JSONObject jsonObject) {
        ErrandList errandList = new ErrandList();
        JSONArray jsonArray = jsonObject.getJSONArray("errandList");
        for (Object json : jsonArray) {
            JSONObject nextAssignment = (JSONObject) json;
            errandList.add(extractErrand(nextAssignment));
        }
        return errandList;
    }

    // EFFECTS: parses errand from JSON object and returns it
    private Errand extractErrand(JSONObject jsonObject) {
        Errand errand = new Errand(jsonObject.getString("title"));
        errand.setDescription(jsonObject.getString("description"));
        errand.setPoints(jsonObject.getInt("points"));
        errand.setCompleteByDate(LocalDate.parse(jsonObject.getString("completeByDate")));
        if (jsonObject.getBoolean("completed")) {
            errand.markCompleted();
        }
        return errand;
    }
}