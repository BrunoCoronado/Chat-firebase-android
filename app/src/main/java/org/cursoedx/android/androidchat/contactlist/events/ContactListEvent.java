package org.cursoedx.android.androidchat.contactlist.events;

import org.cursoedx.android.androidchat.entities.User;

/**
 * Created by TrexT on 18/06/2016.
 */
public class ContactListEvent {
    public final static  int onContactAdded = 0;
    public final static  int onContactChanged = 1;
    public final static  int onContactRemoved = 2;

    private User user;
    private int eventTpe;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEventTpe() {
        return eventTpe;
    }

    public void setEventTpe(int eventTpe) {
        this.eventTpe = eventTpe;
    }
}
