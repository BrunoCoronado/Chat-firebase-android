package org.cursoedx.android.androidchat.lib;

/**
 * Created by TrexT on 17/06/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
