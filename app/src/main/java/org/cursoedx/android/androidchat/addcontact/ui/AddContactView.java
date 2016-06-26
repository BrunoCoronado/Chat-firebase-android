package org.cursoedx.android.androidchat.addcontact.ui;

/**
 * Created by TrexT on 18/06/2016.
 */
public interface AddContactView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contacatNotAdded();
}
