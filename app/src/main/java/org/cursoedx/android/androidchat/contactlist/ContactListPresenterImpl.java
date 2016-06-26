package org.cursoedx.android.androidchat.contactlist;

import org.cursoedx.android.androidchat.contactlist.events.ContactListEvent;
import org.cursoedx.android.androidchat.contactlist.ui.ContactListView;
import org.cursoedx.android.androidchat.entities.User;
import org.cursoedx.android.androidchat.lib.EventBus;
import org.cursoedx.android.androidchat.lib.GreenRobotEventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by TrexT on 18/06/2016.
 */
public class ContactListPresenterImpl implements ContactListPresenter {
    private EventBus evenbus;
    private ContactListView view;
    private ContactListInteractor listInteractor;
    private ContactListSessionInteractor sessionInteractor;

    public ContactListPresenterImpl(ContactListView view) {
        this.view = view;
        evenbus = GreenRobotEventBus.getInstance();
        this.listInteractor = new ContactListInteractorImpl();
        this.sessionInteractor = new ContactListSessionInteractorImpl();
    }

    @Override
    public void onCreate() {
        evenbus.register(this);
    }

    @Override
    public void onDestroy() {
        evenbus.unregister(this);
        listInteractor.destroyListener();
        view = null;
    }

    @Override
    public void onPause() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
    }

    @Override
    public void onResume() {
        sessionInteractor.changeConnectionStatus(User.ONLINE);
        listInteractor.subscribe();
    }

    @Override
    public void signOff() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
        listInteractor.destroyListener();
        sessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return sessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        listInteractor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventTpe()){
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved :
                onContactRemoved(user);
                break;
        }
    }

    private void onContactAdded(User user){
        if(view != null){
            view.onContactAdded(user);
        }
    }

    private void onContactChanged(User user){
        if(view != null){
            view.onConcactChanged(user);
        }
    }

    private void onContactRemoved(User user){
        if(view != null){
            view.onContactRemoved(user);
        }
    }
}
