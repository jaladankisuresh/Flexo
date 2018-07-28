package com.imnotout.flexo.Models;

import java.io.Serializable;

public class VideoItem
        extends MediaItem implements Serializable {
    private String VideoUrl;

    public VideoItem(int type, String url, String videoUrl) {
        super(type, url);
        VideoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }
}