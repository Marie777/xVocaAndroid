package voca.xvocaandroid.models;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Word implements Serializable{
    private String word;
    private int frequency;
    private int weight;

    public Word(JSONObject j) {

        try {
            word = j.has("word") ? j.getString("word"):"";
            frequency = j.has("frequency") ? j.getInt("frequency"):0;
            weight = j.has("weight") ? j.getInt("weight"): 0;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getWeight() {
        return weight;
    }
}
