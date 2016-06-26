package org.cursoedx.android.androidchat.addcontact;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.cursoedx.android.androidchat.addcontact.events.AddContactEvent;
import org.cursoedx.android.androidchat.domain.FirebaseHelper;
import org.cursoedx.android.androidchat.entities.User;
import org.cursoedx.android.androidchat.lib.EventBus;
import org.cursoedx.android.androidchat.lib.GreenRobotEventBus;

/**
 * Created by TrexT on 18/06/2016.
 */
public class AddContactRepositoryImpl implements AddContactRepository {
    private EventBus eventBus;
    private FirebaseHelper helper;

    public AddContactRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void addContact(final String email) {
        final String key = email.replace(".","_");
        Firebase userReference = helper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    Firebase myContacReference = helper.getMyContactsReference();
                    myContacReference.child(key).setValue(user.isOnline());

                    String currentUserKey = helper.getAuthUserEmail();
                    currentUserKey= currentUserKey.replace(".","_");

                    Firebase reverseContactReference = helper.getContactsReference(email);
                    reverseContactReference.child(currentUserKey).setValue(user.ONLINE);

                    postSucces();
                } else {
                    postError();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                postError();
            }

            private void postSucces() {
                post(false);
            }

            private void postError(){
                post(true);
            }

            private void post(boolean error) {
                AddContactEvent event = new AddContactEvent();
                event.setError(error);
                eventBus.post(event);
            }
        });
    }
}
