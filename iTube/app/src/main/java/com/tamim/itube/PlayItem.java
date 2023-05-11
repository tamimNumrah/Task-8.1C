package com.tamim.itube;

import android.database.Cursor;

import java.io.Serializable;

public class PlayItem implements Serializable {
    private String id;
    private String url;

    public PlayItem(String id, String url) {
        this.url = url;
        this.id = id;//UUID.randomUUID().toString();
    }

    public PlayItem(Cursor cursor) {
        this.url = cursor.getString(1);
        this.id = cursor.getString(0);//UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
