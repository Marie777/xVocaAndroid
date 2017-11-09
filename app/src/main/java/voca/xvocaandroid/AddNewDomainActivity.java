package voca.xvocaandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;

import org.json.JSONException;
import org.json.JSONObject;

import voca.xvocaandroid.models.User;

public class AddNewDomainActivity extends AppCompatActivity {

    private static final String TAG ="addDomain" ;
    private String token;
    private String googleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnew_domain);


        token = getIntent().getExtras().getString("token");
        googleId = getIntent().getExtras().getString("googleId");
        Log.d(TAG, "onCreate: "+googleId);

    }

    public void submitBTN(View view){
        JSONObject data = new JSONObject();
        try {
            data.put("googleId", googleId);
            data.put("domainName", ((EditText)findViewById(R.id.etDomainName)).getText());
            data.put("description", ((EditText)findViewById(R.id.etDescription)).getText());
            data.put("mainDomain", ((EditText)findViewById(R.id.etDomain1)).getText());
            data.put("subDomain", ((EditText)findViewById(R.id.etDomain2)).getText());

            String url = "http://10.0.2.2:3000/user/newDomain";
            AuthorizedJsonRequest jsonObjectRequestPDF = new AuthorizedJsonRequest(
                    Request.Method.POST,
                    url,
                    data,
                    response -> {
                        Log.d(TAG, "res: " + response.toString());
                        User userObj = new User(response);
                        Intent intent = new Intent(this, DomainListActivity.class);
                        intent.putExtra("userObj", userObj);
                        intent.putExtra("token", token);
                        startActivity(intent);
                    },
                    (error) -> Log.d(TAG, "error: " + error),
                    token);

            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequestPDF);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
