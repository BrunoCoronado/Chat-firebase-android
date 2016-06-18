package org.cursoedx.android.androidchat.contactlist.ui;

import org.cursoedx.android.androidchat.entities.User;

/**
 * Created by TrexT on 18/06/2016.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onConcactChanged(User user);
    void onContactRemoved(User user);
}
