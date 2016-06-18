package org.cursoedx.android.androidchat.login;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.cursoedx.android.androidchat.domain.FirebaseHelper;
import org.cursoedx.android.androidchat.entities.User;
import org.cursoedx.android.androidchat.lib.EventBus;
import org.cursoedx.android.androidchat.lib.GreenRobotEventBus;
import org.cursoedx.android.androidchat.login.events.LoginEvent;

import java.util.Map;

/**
 * Created by TrexT on 17/06/2016.
 */
public class LoginRepostiroryImpl implements LoginRepository{
    private FirebaseHelper firebaseHelper;
    private Firebase dataReference;
    private Firebase myUserReference;

    public LoginRepostiroryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.dataReference = firebaseHelper.getDataReference();
        this.myUserReference = firebaseHelper.getMyUserReference();
    }

    @Override
    public void signUp(final String email, final String password) {
        dataReference.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                postEvent(LoginEvent.onSignUpSuccess);
                signIn(email, password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUpError, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void signIn(String email, String password) {
        dataReference.authWithPassword(email, password, new Firebase.AuthResultHandler(){
            @Override
            public void onAuthenticated(AuthData authData) {
                myUserReference = firebaseHelper.getMyUserReference();
                myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User currentUser = dataSnapshot.getValue(User.class);

                        if (currentUser == null) {
                            String email = firebaseHelper.getAuthUserEmail();
                            if (email!=null) {
                                currentUser = new User();
                                myUserReference.setValue(currentUser);
                            }
                        }
                        firebaseHelper.changeUserConnectionStatus(User.ONLINE);
                        postEvent(LoginEvent.onSignInSuccess);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {}
                });
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignInError, firebaseError.getMessage());
            }
        });
    }

    @Override
    public void checkSession() {
        postEvent(LoginEvent.onFailedToRecoverSession);
    }

    private void postEvent(int type, String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }

        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type){
        postEvent(type, null);
    }
}
