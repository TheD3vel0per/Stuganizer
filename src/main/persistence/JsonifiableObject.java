package persistence;

import org.json.JSONObject;

/**
 * This is an interface which can be used to turn any list type into a JSON object.
 *
 * It is required to be used for any object type where you
 * want any sort of data to persist.
 *
 * Implement the interface and write out the appropriate methods.
 */
public interface JsonifiableObject {
    public JSONObject toJson();
}
