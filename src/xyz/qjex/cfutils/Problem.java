package xyz.qjex.cfutils;

import org.json.JSONObject;

/**
 * Created by qjex on 2/24/16.
 */
public class Problem {
    private long contestId;
    private String type, index, name;

    public String getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getUniqueName() {
        return contestId + index;
    }

    public Problem(JSONObject json) {
        contestId = json.getLong("contestId");
        type = json.getString("type");
        index = json.getString("index");
        name = json.getString("name");
    }
}
