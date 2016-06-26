package org.cursoedx.android.androidchat.chat;

/**
 * Created by TrexT on 25/06/2016.
 */
public interface ChatInteractor {
    void sendMessage(String msg);
    void setChatRecipient(String recipient);

    void subscribre();
    void unsubscribe();
    void destroyListener();
}
