package org.cursoedx.android.androidchat.contactlist;

/**
 * Created by TrexT on 18/06/2016.
 */
public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
