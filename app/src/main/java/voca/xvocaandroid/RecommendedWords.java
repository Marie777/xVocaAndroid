package voca.xvocaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecommendedWords extends AppCompatActivity {

    private ArrayList<String> wordlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_words);

        //TODO: get word list from intent extras
        getWords("");
        displayWordList();
    }

    public void displayWordList(){

        ListView listView = findViewById(R.id.listViewWords);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_view, wordlist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, WordDetails.class);
            intent.putExtra("Word", ((TextView) view).getText().toString());
            startActivity(intent);
        });
    }

    public void getWords(String categoryName){
        wordlist = new ArrayList<>(10);
        wordlist.add("TEST word0");
        wordlist.add("TEST word1");
        wordlist.add("TEST word2");
        wordlist.add("TEST word3");

    }
}
