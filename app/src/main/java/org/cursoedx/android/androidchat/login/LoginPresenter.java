package org.cursoedx.android.androidchat.login;

import org.cursoedx.android.androidchat.login.events.LoginEvent;

/**
 * Created by TrexT on 17/06/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
