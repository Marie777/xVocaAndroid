package voca.xvocaandroid;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

public class XVocaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().subscribeToTopic("news");
    }
}
