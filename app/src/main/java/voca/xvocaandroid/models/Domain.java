package voca.xvocaandroid.models;


import java.util.ArrayList;

public class Domain {
    private String name;
    private String description;
    private String mainDomain;
    private String subDomain;
    private ArrayList<Category> categoryList;

    public Domain(String name, String description, String mainDomain, String subDomain, ArrayList<Category> categoryList) {
        this.name = name;
        this.description = description;
        this.mainDomain = mainDomain;
        this.subDomain = subDomain;
        this.categoryList = categoryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainDomain() {
        return mainDomain;
    }

    public void setMainDomain(String mainDomain) {
        this.mainDomain = mainDomain;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
