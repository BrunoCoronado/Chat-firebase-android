package org.cursoedx.android.androidchat.chat;

/**
 * Created by TrexT on 25/06/2016.
 */
public class ChatInteractorImpl implements ChatInteractor {
    ChatRepository repository;

    public ChatInteractorImpl() {
        this.repository = new ChatRepositoryImpl();
    }

    @Override
    public void sendMessage(String msg) {
        repository.sendMessage(msg);
    }

    @Override
    public void setChatRecipient(String recipient) {
        repository.setChatRecipient(recipient);
    }

    @Override
    public void subscribre() {
        repository.subscribre();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }
}
