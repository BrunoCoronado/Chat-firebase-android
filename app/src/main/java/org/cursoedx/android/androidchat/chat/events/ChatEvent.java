package org.cursoedx.android.androidchat.chat.events;

import org.cursoedx.android.androidchat.entities.ChatMessage;

/**
 * Created by TrexT on 25/06/2016.
 */
public class ChatEvent {
    ChatMessage message;

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }
}
