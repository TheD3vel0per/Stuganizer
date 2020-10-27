package persistence;

import org.json.JSONObject;

public interface JsonifiableObject {
    public JSONObject toJson();
}
