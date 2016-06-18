package org.cursoedx.android.androidchat.login;

import org.cursoedx.android.androidchat.domain.FirebaseHelper;

/**
 * Created by TrexT on 17/06/2016.
 */
public class LoginRepostiroryImpl implements LoginRepository{
    private FirebaseHelper firebaseHelper;

    public LoginRepostiroryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
    }

    @Override
    public void signUp(String email, String password) {

    }

    @Override
    public void signIn(String email, String password) {

    }

    @Override
    public void checkSession() {

    }
}
