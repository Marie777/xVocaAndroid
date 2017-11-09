package voca.xvocaandroid.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class WordDetailsObj implements Serializable {
    private String word;
    private String translate;
    private ArrayList<Sentence> sentenceList = new ArrayList<>();

    public WordDetailsObj(JSONObject j) {

        try {
            word = j.has("word") ? j.getString("word"):"";
            translate = j.has("translate") ? j.getString("translate"):"";

            JSONArray jSentences = j.getJSONArray("sentences");
            for(int i = 0; i < jSentences.length(); i++) {
                JSONObject s = jSentences.getJSONObject(i);
                sentenceList.add(new Sentence(s));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sentence> getSentenceList() {
        return sentenceList;
    }

    public String getWord() {
        return word;
    }

    public String getTranslate() {
        return translate;
    }
}
