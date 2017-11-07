package voca.xvocaandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import voca.xvocaandroid.models.Category;
import voca.xvocaandroid.models.Domain;
import voca.xvocaandroid.models.Quiz;
import voca.xvocaandroid.models.User;

public class CategoryWordLists extends AppCompatActivity {

    private  ArrayList<String> categorieNames ;
    private Domain domain;
    private final int RC_FILE_PICKED = 0;
    private final String TAG = "Upload";

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
                startUploadIntent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RC_FILE_PICKED:
                if(resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    uploadPDF(uri);
                }
                break;
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

    private void startUploadIntent(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        startActivityForResult(intent, RC_FILE_PICKED);
    }

    public String encodePDF(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            if(inputStream == null) {
                return null;
            }
            byte[] fileContent = IOUtils.toByteArray(inputStream);

            return Base64.encodeToString(fileContent, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void uploadPDF(Uri uri) {
        String b64 = encodePDF(uri);
        String title = uri.toString();

        JSONObject data = new JSONObject();
        try {
            data.put("file", b64);
            data.put("domain", domain.getName());
            data.put("title", title);
            String url = "http://10.0.2.2:3000/file/pdf";
            JsonObjectRequest jsonObjectRequestPDF = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    data,
                    response -> Log.d(TAG, "uploadPDF: " + response.toString()),
                    (error) -> Log.d(TAG, "error: " + error));

            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequestPDF);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
