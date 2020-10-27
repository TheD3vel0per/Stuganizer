package persistence;

import org.json.JSONObject;

public interface Jsonifiable {
    public JSONObject toJson();
}
