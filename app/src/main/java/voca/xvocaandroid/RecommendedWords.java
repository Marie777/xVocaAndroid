package voca.xvocaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecommendedWords extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_words);
        ArrayList<String> WOrds = new ArrayList<>(10);
        WOrds.add("TEST word0");
        WOrds.add("TEST word1");
        WOrds.add("TEST word2");
        WOrds.add("TEST word3");

        ListView listView = findViewById(R.id.listViewWords);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_view, WOrds);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, WordDetails.class);
            intent.putExtra("Word", ((TextView) view).getText().toString());
            startActivity(intent);
        });


    }

}
