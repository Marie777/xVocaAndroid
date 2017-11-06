package voca.xvocaandroid;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DomainList extends AppCompatActivity {

    private ArrayList<String> domains;
    private final static String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_list);

        Toolbar toolbar = findViewById(R.id.toolbarDomain);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //TODO: get domain list from intent extras
        getDomainList("");
        displayDomainList();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_domain_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_domain:
                Intent intent = new Intent(this, ADDNewDomain.class);
                //intent.putExtra("", ((TextView) view).getText().toString());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayDomainList(){

        ListView listView = findViewById(R.id.listViewDomains);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_view, domains);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, CategoryWordLists.class);
            intent.putExtra("domain name", ((TextView) view).getText().toString());
            startActivity(intent);
        });
    }

    //TODO: get info from server
    public void getDomainList(String userName){
        domains = new ArrayList<>(10);
        domains.add("TEST DOMAIN1");
        domains.add("TEST DOMAIN2");
        domains.add("TEST DOMAIN3");
        domains.add("TEST DOMAIN4");
    }
}

/*
        //JSON request:
        TextView tvTest = findViewById(R.id.tvTest);
        String url = "http://10.0.2.2:3000/domain/59f33ac2d8fbcc2b54cfaa64";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> tvTest.setText("response "+ response.toString()),
                (error) -> tvTest.setText(error.toString()));

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        */


/*
    //GET all users
    String urlGetUser = "http://10.0.2.2:3000/user/59fc28b2b667a02a0859ab39";
    JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(
            Request.Method.GET,
            urlGetUser,
            null,
            (response) -> {
                try {
                    String googleId = "";
                    String domainName = "";
                    String description = "";
                    String mainDomain = "";
                    String subDomain = "";
                    String categories = "";


                    JSONArray JSONdomains = response.getJSONArray("domains");
                    tvTest.setText("response " + JSONdomains +"\n\n ----\n");
                    tvTest.append("length: " + JSONdomains.length() + "\n");
                    for(int i = 0; i < JSONdomains.length(); i++){
                        JSONObject d = JSONdomains.getJSONObject(i);

                        //TODO: java object:  domains, categories, words
                        //Check Field exist => not null
                        if(d.has("googleId")){
                            googleId = d.getString("googleId");
                        }
                        if(d.has("domainName")){
                            domainName = d.getString("domainName");
                        }
                        if(d.has("description")){
                            description = d.getString("description");
                        }
                        if(d.has("mainDomain")){
                            mainDomain = d.getString("mainDomain");
                        }
                        if(d.has("subDomain")){
                            subDomain = d.getString("subDomain");
                        }
                        if(d.has("categories")){
                            categories = d.getString("categories");
                        }
                        tvTest.append("googleId " + googleId + "\n");
                        tvTest.append("domainName " + domainName + "\n");
                        tvTest.append("description: " + description + "\n");
                        tvTest.append("mainDomain: " + mainDomain + "\n");
                        tvTest.append("subDomain: " + subDomain + "\n");
                        tvTest.append("categories: " + categories + "\n");
                    }
                    tvTest.append("finished" + domainName + "\n" );


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            },
            (error) -> tvTest.setText(error.toString()));

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest2);
*/



//listView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
