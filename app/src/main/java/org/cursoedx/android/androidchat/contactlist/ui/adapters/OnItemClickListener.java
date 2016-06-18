package org.cursoedx.android.androidchat.contactlist.ui.adapters;

import org.cursoedx.android.androidchat.entities.User;

/**
 * Created by TrexT on 18/06/2016.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
