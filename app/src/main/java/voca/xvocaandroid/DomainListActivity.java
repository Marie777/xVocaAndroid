package voca.xvocaandroid;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;


import com.android.volley.Request;

import java.util.ArrayList;

import voca.xvocaandroid.models.Domain;
import voca.xvocaandroid.models.User;

public class DomainListActivity extends AppCompatActivity {

    private static final String TAG = "DomainListActivity";
    private ArrayList<String> domainNames;
    private User user;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_list);

        Toolbar toolbar = findViewById(R.id.toolbarDomain);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        user = (User) getIntent().getExtras().get("userObj");
        token = getIntent().getExtras().getString("token");

        getDomainList(user.getDomainList());
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
                Intent intent = new Intent(this, AddNewDomainActivity.class);
                intent.putExtra("token", token);
                intent.putExtra("googleId", user.getGoogleId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayDomainList(){

        ListView listView = findViewById(R.id.listViewDomains);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_view, domainNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, CategoryWordListsActivity.class);
            intent.putExtra("domain", user.getDomain(position));
            intent.putExtra("token", token);
            startActivity(intent);
        });
    }

    public void getDomainList(ArrayList<Domain> domainsObj){
       domainNames = new ArrayList<>();
        domainsObj.forEach(d -> domainNames.add(d.getName()));
    }

    public void mockUser(View view) {

        //JSON request:
        String url = getString(R.string.x_voca_server) + "/user/mockUser";
        AuthorizedJsonRequest jsonObjectRequest = new AuthorizedJsonRequest(
                Request.Method.POST,
                url,
                null,
                response -> Log.d(TAG, "mockUser: " + response.toString()),
                (error) -> Log.d(TAG, "mockUser: "),
                token);

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}

