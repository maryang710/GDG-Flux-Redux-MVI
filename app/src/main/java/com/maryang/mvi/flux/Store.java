package com.maryang.mvi.flux;

public abstract class Store {

    protected void emitChange() {
        Dispatcher.emitChange(changeEvent());
    }

    protected abstract ChangeEvent changeEvent();

    public interface ChangeEvent {
    }
}
