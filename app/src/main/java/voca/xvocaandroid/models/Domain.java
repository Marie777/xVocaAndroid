package voca.xvocaandroid.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Domain {
    private String domainName;
    private String description;
    private String mainDomain;
    private String subDomain;
    private ArrayList<Category> categoryList = new ArrayList<>();

    public Domain(JSONObject j) {

        try {
            domainName = j.has("domainName") ? j.getString("domainName"):"";
            description = j.has("description") ? j.getString("description"):"";
            mainDomain = j.has("mainDomain") ? j.getString("mainDomain"):"";
            subDomain = j.has("subDomain") ? j.getString("subDomain"):"";

            JSONArray jCategory = j.getJSONArray("categories");
            for(int i = 0; i < jCategory.length(); i++) {
                JSONObject c = jCategory.getJSONObject(i);
                categoryList.add(new Category(c));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getName() {
        return domainName;
    }

    public String getDescription() {
        return description;
    }

    public String getMainDomain() {
        return mainDomain;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }
}
