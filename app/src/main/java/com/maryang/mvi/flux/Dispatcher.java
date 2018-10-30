package com.maryang.mvi.flux;

import org.greenrobot.eventbus.EventBus;

public class Dispatcher {

    private static EventBus bus = EventBus.getDefault();

    public static void dispatch(Action action) {
        bus.post(action);
    }

    public static void emitChange(Store.ChangeEvent event) {
        bus.post(event);
    }

    public static void register(Object obj) {
        bus.register(obj);
    }

    public static void unregister(Object obj) {
        bus.unregister(obj);
    }
}
