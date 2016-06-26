package org.cursoedx.android.androidchat.chat.ui;

import org.cursoedx.android.androidchat.entities.ChatMessage;

/**
 * Created by TrexT on 25/06/2016.
 */
public interface ChatView {
    void onMessageReceived(ChatMessage msg);
}
