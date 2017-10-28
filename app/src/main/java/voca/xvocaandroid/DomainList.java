package voca.xvocaandroid;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


import java.util.ArrayList;

public class DomainList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_list);

        ArrayList<String> domains = new ArrayList<>(10);
        domains.add("TEST DOMAIN1");
        domains.add("TEST DOMAIN2");
        domains.add("TEST DOMAIN3");
        domains.add("TEST DOMAIN4");

        ListView listView = findViewById(R.id.listViewDomains);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_view, domains);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("TAG", ((TextView) view).getText().toString());
            //--TODO -- new activity for word list category
        });


        Toolbar toolbar = findViewById(R.id.toolbarBotton);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

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
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
                //Log.d("TAG","here");
                //TODO: redirect to add domain activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}



//listView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
