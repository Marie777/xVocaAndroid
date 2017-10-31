package voca.xvocaandroid;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONObject;

import java.util.ArrayList;

public class DomainList extends AppCompatActivity {

    private ArrayList<String> domains;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_list);

        Toolbar toolbar = findViewById(R.id.toolbarDomain);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        displayDomainList();

        //JSON request:
        TextView tvTest = findViewById(R.id.tvTest);
        String url = "http://10.0.2.2:3000/domain/59f33ac2d8fbcc2b54cfaa64";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> tvTest.setText("responce "+ response.toString()),
                (error) -> tvTest.setText(error.toString()));

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

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

        getDomainList("");

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



//listView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
