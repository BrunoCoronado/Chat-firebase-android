package org.cursoedx.android.androidchat.contactlist;

import org.cursoedx.android.androidchat.contactlist.events.ContactListEvent;

/**
 * Created by TrexT on 18/06/2016.
 */
public interface ContactListPresenter {
    void onCreate();
    void onDestroy();
    void onPause();
    void onResume();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);
}
