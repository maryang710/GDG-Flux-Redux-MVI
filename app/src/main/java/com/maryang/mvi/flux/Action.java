package com.maryang.mvi.flux;

import java.util.HashMap;

public class Action {

    private String type;
    private HashMap<String, Object> data;

    private Action() {
    }

    public String getType() {
        return type;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public static Action with(String type) {
        if (type == null) throw new IllegalArgumentException("action may not be null.");
        Action action = new Action();
        action.type = type;
        action.data = new HashMap<>();
        return action;
    }

    public Action data(String key, Object value) {
        if (key == null) throw new IllegalArgumentException("Key may not be null.");
        if (value == null) throw new IllegalArgumentException("Value may not be null.");
        data.put(key, value);
        return this;
    }
}
