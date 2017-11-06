package voca.xvocaandroid.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {
    private String googleId;
    private String userName;
    private int age;
    private String gender;
    private String nativeLanguage;
    private ArrayList<Domain> domainList = new ArrayList<>();

    public User(JSONObject j) {

        try {
            googleId = j.has("googleId") ? j.getString("googleId"):"";
            userName = j.has("userName") ? j.getString("userName"):"";
            age = j.has("age") ? j.getInt("age"): 0;
            gender = j.has("gender") ? j.getString("gender"):"";
            nativeLanguage = j.has("nativeLanguage") ? j.getString("nativeLanguage"):"";

            JSONArray jDomains = j.getJSONArray("domains");
            for(int i = 0; i < jDomains.length(); i++) {
                JSONObject d = jDomains.getJSONObject(i);
                domainList.add(new Domain(d));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getGoogleId() {
        return googleId;
    }

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public ArrayList<Domain> getDomainList() {
        return domainList;
    }
}
