package org.cursoedx.android.androidchat.chat;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.cursoedx.android.androidchat.chat.events.ChatEvent;
import org.cursoedx.android.androidchat.domain.FirebaseHelper;
import org.cursoedx.android.androidchat.entities.ChatMessage;
import org.cursoedx.android.androidchat.lib.EventBus;
import org.cursoedx.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by TrexT on 25/06/2016.
 */
public class ChatRepositoryImpl implements ChatRepository {
    private String receiver;
    private FirebaseHelper helper;
    private EventBus eventBus;
    private ChildEventListener childEventListener;

    public ChatRepositoryImpl() {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.helper = FirebaseHelper.getInstance();
    }

    @Override
    public void sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(helper.getAuthUserEmail());
        chatMessage.setMsg(msg);

        Firebase chatReference = helper.getChatReference(receiver);
        chatReference.push().setValue(chatMessage);
    }

    @Override
    public void setChatRecipient(String recipient) {
        this.receiver = recipient;
    }

    @Override
    public void subscribre() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String msgSender = chatMessage.getSender();

                    chatMessage.setSendByMe(msgSender.equals(helper.getAuthUserEmail()));

                    ChatEvent chatEvent= new ChatEvent();
                    chatEvent.setMessage(chatMessage);
                    eventBus.post(chatEvent);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            };
        }
        helper.getChatReference(receiver).addChildEventListener(childEventListener);
    }

    @Override
    public void unsubscribe() {
        if (childEventListener != null) {
            helper.getChatReference(receiver).removeEventListener(childEventListener);
        }
    }

    @Override
    public void destroyListener() {
        childEventListener = null;
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}
