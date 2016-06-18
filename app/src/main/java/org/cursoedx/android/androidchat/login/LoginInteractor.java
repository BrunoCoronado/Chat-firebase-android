package org.cursoedx.android.androidchat.login;

/**
 * Created by TrexT on 17/06/2016.
 */
public interface LoginInteractor {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
