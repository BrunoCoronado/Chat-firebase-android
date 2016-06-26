package org.cursoedx.android.androidchat.chat;

import org.cursoedx.android.androidchat.chat.events.ChatEvent;
import org.cursoedx.android.androidchat.chat.ui.ChatView;
import org.cursoedx.android.androidchat.entities.User;
import org.cursoedx.android.androidchat.lib.EventBus;
import org.cursoedx.android.androidchat.lib.GreenRobotEventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by TrexT on 25/06/2016.
 */
public class ChatPresenterImpl implements ChatPresenter {
    private EventBus eventBus;
    private ChatView view;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor sessionInteractor;
    public ChatPresenterImpl(ChatView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.chatInteractor = new ChatInteractorImpl();
        this.sessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onPasue() {
        chatInteractor.unsubscribe();
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onResume() {
        chatInteractor.subscribre();
        sessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyListener();
        view = null;
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if (view != null) {
            view.onMessageReceived(event.getMessage());
        }
    }

    @Override
    public void setChatRecipient(String recipient) {
        chatInteractor.setChatRecipient(recipient);
    }
}
