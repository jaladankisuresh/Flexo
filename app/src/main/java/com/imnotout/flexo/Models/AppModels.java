package com.imnotout.flexo.Models;

import java.io.Serializable;
import java.util.List;

public class AppModels {
    public static class MediaCollage implements Serializable {
        List<MediaLayout> mediaCollection;
        int colCount;
        float cellAspectRatio;

        public MediaCollage(List<MediaLayout> mediaCollection, int colCount, float cellAspectRatio) {
            this.mediaCollection = mediaCollection;
            this.colCount = colCount;
            this.cellAspectRatio = cellAspectRatio;
        }
        public List<MediaLayout> getMediaCollection() {
            return mediaCollection;
        }

        public int getColCount() {
            return colCount;
        }

        public float getCellAspectRatio() {
            return cellAspectRatio;
        }
    }
    public static class MediaLayout implements Serializable {
        String url;
        int rowSpan = 1;
        int colSpan = 1;

        public MediaLayout(String url, int rowSpan, int colSpan) {
            this.url = url;
            this.rowSpan = rowSpan;
            this.colSpan = colSpan;
        }
        public MediaLayout(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }

        public int getRowSpan() {
            return rowSpan;
        }

        public int getColSpan() {
            return colSpan;
        }
    }
    public static class VideoMediaLayout extends MediaLayout {
        String videoUrl;

        public VideoMediaLayout(String videoUrl, String url, int rowSpan, int colSpan) {
            super(url, rowSpan, colSpan);
            this.videoUrl = videoUrl;
        }
        public VideoMediaLayout(String videoUrl, String url) {
            super(url);
            this.videoUrl = videoUrl;
        }
        public String getVideoUrl() {
            return videoUrl;
        }
    }
}
