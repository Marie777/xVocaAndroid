package voca.xvocaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import voca.xvocaandroid.models.User;

/**
 * A login screen that offers login via google's auth.
 */

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private final static String TAG = "Login";

    private final static int RC_SIGN_IN_GOOGLE = 0;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.server_client_id))
                .requestProfile()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // TODO
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(this,"Sign in succeeded " + acct.getEmail() ,Toast.LENGTH_SHORT).show();

            String token = acct.getIdToken();

            JSONObject data = new JSONObject();
            try {
                data.put("token", token);

                String url = "http://10.0.2.2:3000/user/tokenlogin/google";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        data,
                        response -> {
                            Log.d(TAG, "success: "+response.toString());
                            Intent intent = new Intent(this, DomainList.class);
                            //TODO: create user(jsonObject) -> putExtra
                            //intent.putExtra("user", response.toString());
                            User userObj = new User(response);
                            intent.putExtra("userObj", userObj);
                            startActivity(intent);
                            },
                        error -> Log.e(TAG, "err: "+error.toString()));

                MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, acct.getIdToken());

        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this,"Sign in failed ",Toast.LENGTH_SHORT).show();
        }

        //getUser("112470571093225051385");

    }

    /*

    public void getUser(String googleId){

        //---get User----:
        try {
            JSONObject data = new JSONObject();
            data.put("googleId", googleId);
            String url = "http://10.0.2.2:3000/user/googleId";
            JsonObjectRequest jsonObjectRequestUser = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    data,
                    response -> {
                        Log.d(TAG_USER, response.toString());
                        //Intent intent = new Intent(this, DomainList.class);
                        //intent.putExtra("user", response.toString());
                        //startActivity(intent);
                    },
                    error -> Log.e(TAG_USER, error.toString()));

            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequestUser);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    */
}