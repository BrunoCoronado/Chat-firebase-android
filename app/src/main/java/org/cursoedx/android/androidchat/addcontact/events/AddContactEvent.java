package org.cursoedx.android.androidchat.addcontact.events;

/**
 * Created by TrexT on 18/06/2016.
 */
public class AddContactEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
