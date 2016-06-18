package org.cursoedx.android.androidchat.contactlist;

/**
 * Created by TrexT on 18/06/2016.
 */
public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);
}
