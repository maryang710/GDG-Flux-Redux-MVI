package com.maryang.mvi.mvi;

import java.util.HashMap;

public class MviIntent {

    private String type;
    private HashMap<String, Object> data;

    protected MviIntent() {
    }

    public String getType() {
        return type;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public static MviIntent with(String type) {
        if (type == null) throw new IllegalArgumentException("intent may not be null.");
        MviIntent intent = new MviIntent();
        intent.type = type;
        intent.data = new HashMap<>();
        return intent;
    }

    public MviIntent data(String key, Object value) {
        if (key == null) throw new IllegalArgumentException("Key may not be null.");
        if (value == null) throw new IllegalArgumentException("Value may not be null.");
        data.put(key, value);
        return this;
    }
}
