package voca.xvocaandroid.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable{
    private String categoryName;
    private ArrayList<Word> wordList = new ArrayList<>();

    public Category(JSONObject j) {

        try {
            categoryName = j.has("categoryName") ? j.getString("categoryName"):"";
            JSONArray jWords = j.getJSONArray("wordList");
            for(int i = 0; i < jWords.length(); i++) {
                JSONObject w = jWords.getJSONObject(i);
                wordList.add(new Word(w));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.wordList = wordList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ArrayList<Word> getWordList() {
        return wordList;
    }
}
