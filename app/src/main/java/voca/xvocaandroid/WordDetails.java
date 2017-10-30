package voca.xvocaandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordDetails extends AppCompatActivity {

    private ArrayList<String> sentences;
    private Integer[] mThumbIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_details);

        Toolbar toolbar = findViewById(R.id.toolbarWordDetails);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        displaySentenceList();
        displayImages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_word_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sentence_origin:
                Toast.makeText(this,"Map",Toast.LENGTH_SHORT).show();
                //Log.d("TAG","here");
                //TODO: redirect to map activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayImages(){

        Integer[] mThumbIds = {
                R.drawable.tile1,
                R.drawable.tile2,
                R.drawable.tile3,
                R.drawable.tile4,
                R.drawable.tile5,
                R.drawable.tile6,
                R.drawable.tile7,
                R.drawable.tile8,
        };

        GridView gridViewImg = findViewById(R.id.gridViewImages);
        //gridViewImg.setNumColumns(3);
        gridViewImg.setAdapter(new ImageAdapter(this, mThumbIds));

    }

    public void displaySentenceList()
    {
        getSentences("");

        ListView listView = findViewById(R.id.listViewSentences);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_view, sentences);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            //TODO: Like?
            Toast.makeText(this,"Like",Toast.LENGTH_SHORT).show();
        });
    }

    public void getSentences(String word){
        sentences = new ArrayList<>(10);
        sentences.add("TEST Sentence");
        sentences.add("TEST Sentence1");
        sentences.add("TEST Sentence2");
        sentences.add("TEST Sentence3");

    }

    //TODO: add new sentence
    public void newSentence(){

    }

}
