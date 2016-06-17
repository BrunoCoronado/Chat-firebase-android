package org.cursoedx.android.androidchat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by TrexT on 17/06/2016.
 */
public class AndroidChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
