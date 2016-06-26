package org.cursoedx.android.androidchat.chat;

import org.cursoedx.android.androidchat.chat.events.ChatEvent;

/**
 * Created by TrexT on 25/06/2016.
 */
public interface ChatPresenter {
    void onPasue();
    void onResume();
    void onCreate();
    void onDestroy();

    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
    void setChatRecipient(String recipient);
}
