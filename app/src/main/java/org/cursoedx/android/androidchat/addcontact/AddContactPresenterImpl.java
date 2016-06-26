package org.cursoedx.android.androidchat.addcontact;

import android.view.View;

import org.cursoedx.android.androidchat.addcontact.AddContactPresenter;
import org.cursoedx.android.androidchat.addcontact.events.AddContactEvent;
import org.cursoedx.android.androidchat.addcontact.ui.AddContactView;
import org.cursoedx.android.androidchat.lib.EventBus;
import org.cursoedx.android.androidchat.lib.GreenRobotEventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by TrexT on 18/06/2016.
 */
public class AddContactPresenterImpl implements AddContactPresenter {
    private EventBus eventBus;
    private AddContactInteractor interactor;
    private AddContactView view;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if (view != null) {
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showInput();

            if (event.isError()) {
                view.contacatNotAdded();
            } else {
                view.contactAdded();
            }
        }
    }
}
