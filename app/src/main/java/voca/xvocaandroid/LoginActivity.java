package voca.xvocaandroid;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.Result;
import com.google.firebase.FirebaseApp;

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

        OptionalPendingResult<GoogleSignInResult> r = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

        new Thread(() -> {
            GoogleSignInResult res = r.await();
            runOnUiThread(() -> handleSignInResult(res));
        }).start();

        findViewById(R.id.sign_in_button).setOnClickListener(this);

        if (getIntent().hasExtra("notificationID")) {
            int notificationID = getIntent().getIntExtra("notificationID", 0);
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            mNotificationManager.cancel(notificationID);
        }
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

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(this,"Sign in succeeded " + acct.getEmail() ,Toast.LENGTH_SHORT).show();

            String token = acct.getIdToken();
            String url = "http://10.0.2.2:3000/user/tokenlogin/google";
            AuthorizedJsonRequest jsonObjectRequest = new AuthorizedJsonRequest(
                    Request.Method.POST,
                    url,
                    null,
                    response -> {
                        User userObj = new User(response);
                        Intent intent = new Intent(this, DomainList.class);
                        intent.putExtra("userObj", userObj);
                        intent.putExtra("token", token);
                        startActivity(intent);
                        },
                    error -> Log.e(TAG, "err: "+error.toString()),
                    token);
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

            Log.d(TAG, acct.getIdToken());

        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this,"Sign in failed ",Toast.LENGTH_SHORT).show();
        }

    }
}