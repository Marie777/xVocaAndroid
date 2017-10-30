package voca.xvocaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryWordLists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_word_lists);

        ArrayList<String> Categories = new ArrayList<>(10);
        Categories.add("TEST Category");
        Categories.add("TEST Category1");
        Categories.add("TEST Category2");
        Categories.add("TEST Category3");

        ListView listView = findViewById(R.id.listViewCategories);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_view, Categories);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, RecommendedWords.class);
            intent.putExtra("Category name", ((TextView) view).getText().toString());
            startActivity(intent);
        });


        Toolbar toolbar = findViewById(R.id.toolbarCategories);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category_word_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.progress_monitor:
                Toast.makeText(this,"progress_monitor",Toast.LENGTH_SHORT).show();
                //TODO: redirect...
                return true;
            case R.id.quiz:
                Toast.makeText(this,"quiz",Toast.LENGTH_SHORT).show();
                //TODO: redirect...
                return true;
            case R.id.upload_files:
                Toast.makeText(this,"upload_files",Toast.LENGTH_SHORT).show();
                //TODO: redirect...
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
