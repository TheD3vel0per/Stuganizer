package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This is an interface which can be used to turn any list type into a JSON array.
 *
 * It is required to be used for any array, list, or collection type where you
 * want any sort of data to persist.
 *
 * Implement the interface and write out the appropriate methods.
 */
public interface JsonifiableArray {
    public JSONArray toJson();
}
