package org.cursoedx.android.androidchat.login;

/**
 * Created by TrexT on 17/06/2016.
 */
public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();
}
