package voca.xvocaandroid.models;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Sentence implements Serializable {
    private String theSentence;
    private String userId;
    private int countLike;
    private double lat;
    private double lng;

    public Sentence(JSONObject j) {

        try {
            theSentence = j.has("sentence") ? j.getString("sentence") : "";
            userId = j.has("user") ? j.getString("user") : "";
            countLike = j.has("countLike") ? j.getInt("countLike") : 0;
            JSONObject location = j.has("location") ? j.getJSONObject("location") : null;
            lat = location.has("lat") ? location.getDouble("lat") : 0.0;
            lng = location.has("lng") ? location.getDouble("lng") : 0.0;

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getTheSentence() {
        return theSentence;
    }

    public String getUserId() {
        return userId;
    }

    public int getCountLike() {
        return countLike;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
