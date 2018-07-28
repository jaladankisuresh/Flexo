package com.imnotout.flexo.Models;
import java.io.Serializable;

public class MediaItem implements Serializable {
    private int type;
    private String url;

    public MediaItem(int type, String url) {
        this.type = type;
        this.url = url;
    }

    public int getType() {
        return type;
    }
    public String getUrl() {
        return url;
    }

}

