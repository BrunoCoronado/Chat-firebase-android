package org.cursoedx.android.androidchat.addcontact;

import org.cursoedx.android.androidchat.addcontact.events.AddContactEvent;

/**
 * Created by TrexT on 18/06/2016.
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
