package voca.xvocaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import voca.xvocaandroid.models.Category;
import voca.xvocaandroid.models.Word;

public class RecommendedWordsActivity extends AppCompatActivity {

    private ArrayList<String> wordlist;
    private Category category;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_words);

        category = (Category) getIntent().getExtras().get("Category");
        token = getIntent().getExtras().getString("token");
        getWords(category.getWordList());
        displayWordList();
    }

    public void displayWordList(){

        ListView listView = findViewById(R.id.listViewWords);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_view, wordlist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, WordDetailsActivity.class);
            intent.putExtra("Word", ((TextView) view).getText().toString());
            intent.putExtra("token", token);
            startActivity(intent);
        });
    }

    public void getWords(ArrayList<Word> wordListObj){
        wordlist = new ArrayList<>(10);
        wordListObj.forEach(w -> wordlist.add(w.getWord()));

    }
}
