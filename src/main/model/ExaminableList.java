package model;

import org.json.JSONArray;
import persistence.JsonifiableArray;

import java.util.ArrayList;
import java.util.List;

public class ExaminableList implements JsonifiableArray {
    private List<Examinable> examinables;

    /**
     * MODIFIES: this
     * EFFECTS : Instantiates the ExaminableList
     */
    public ExaminableList() {
        this.examinables = new ArrayList<>();
    }

    /**
     * EFFECTS: Returns the quantity of examinables in the list
     */
    public int size() {
        int size = 0;
        for (Examinable next : this.examinables) {
            if (next != Examinable.NULL_EXAMINABLE) {
                size++;
            }
        }
        return size;
    }

    /**
     * EFFECTS: Returns the maximum index of the examinables list, if there are
     *          no elements in the list, returns -1
     */
    public int maxIndex() {
        return this.examinables.size() - 1;
    }

    /**
     * MODIFIES: this
     * EFFECTS : Adds the given examinable to the examinable list, and returns its index
     */
    public int add(Examinable examinableToAdd) {
        this.examinables.add(examinableToAdd);
        return this.size() - 1;
    }

    /**
     * EFFECTS : Returns the examinable located at the given index, if none exists, returns null
     */
    public Examinable get(int index) {
        if (index > this.maxIndex()) {
            return null;
        }
        return this.examinables.get(index);
    }

    /**
     * REQUIRES: There must be an examinable at the given index
     * MODIFIES: this
     * EFFECTS : Removes the examinable at the given index
     */
    public void remove(int index) {
        this.examinables.set(index, Examinable.NULL_EXAMINABLE);
    }

    /**
     * EFFECTS: Returns true if the list is empty, otherwise false
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * EFFECTS: Returns a JSONArray representing the examinable list
     */
    @Override
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();
        for (Examinable next : this.examinables) {
            if (next != Examinable.NULL_EXAMINABLE) {
                jsonArray.put(next.toJson());
            }
        }
        return jsonArray;
    }

}
