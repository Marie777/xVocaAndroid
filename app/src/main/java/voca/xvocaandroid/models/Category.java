package voca.xvocaandroid.models;


import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<String> wordList;

    public Category(String name, ArrayList<String> wordList) {
        this.name = name;
        this.wordList = wordList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public void setWordList(ArrayList<String> wordList) {
        this.wordList = wordList;
    }
}
