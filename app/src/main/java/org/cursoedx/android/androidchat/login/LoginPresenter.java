package org.cursoedx.android.androidchat.login;

/**
 * Created by TrexT on 17/06/2016.
 */
public interface LoginPresenter {
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
