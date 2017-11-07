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

import voca.xvocaandroid.models.Category;
import voca.xvocaandroid.models.Domain;
import voca.xvocaandroid.models.Quiz;
import voca.xvocaandroid.models.User;

public class CategoryWordLists extends AppCompatActivity {

    private  ArrayList<String> categorieNames ;
    private Domain domain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_word_lists);

        Toolbar toolbar = findViewById(R.id.toolbarCategories);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        domain = (Domain) getIntent().getExtras().get("domain");
        getCategories(domain.getCategoryList());
        displayCategoryList();

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
                Intent intent = new Intent(this, QuizActivity.class);
                startActivity(intent);
                return true;
            case R.id.upload_files:
                Toast.makeText(this,"upload_files",Toast.LENGTH_SHORT).show();
                //TODO: redirect...
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayCategoryList(){


        ListView listView = findViewById(R.id.listViewCategories);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_view, categorieNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, RecommendedWords.class);
            intent.putExtra("Category", domain.getCategory(position));
            startActivity(intent);
        });
    }

    public void getCategories(ArrayList<Category> categoryObj){
        categorieNames = new ArrayList<>();
        categoryObj.forEach(c -> categorieNames.add(c.getCategoryName()));

    }
}
