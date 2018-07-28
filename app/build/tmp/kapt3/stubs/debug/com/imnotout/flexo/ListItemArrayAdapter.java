package com.imnotout.flexo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0003()*B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\u001b\u001a\u00020\u0012H\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0012H\u0016J\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u0012H\u0016J\u001c\u0010 \u001a\u00020!2\n\u0010\"\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0012H\u0016J\u001c\u0010#\u001a\u00060\u0002R\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0012H\u0016J\u0014\u0010\'\u001a\u00020!2\n\u0010\"\u001a\u00060\u0002R\u00020\u0000H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u0006+"}, d2 = {"Lcom/imnotout/flexo/ListItemArrayAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/imnotout/flexo/ListItemArrayAdapter$ListItemViewHolder;", "cntx", "Landroid/content/Context;", "collection", "", "Lcom/imnotout/flexo/Models/MediaItem;", "(Landroid/content/Context;Ljava/util/List;)V", "getCntx", "()Landroid/content/Context;", "getCollection", "()Ljava/util/List;", "dataSourceFactory", "Lcom/google/android/exoplayer2/upstream/DataSource$Factory;", "getDataSourceFactory", "()Lcom/google/android/exoplayer2/upstream/DataSource$Factory;", "orientation", "", "getOrientation", "()I", "player", "Lcom/google/android/exoplayer2/SimpleExoPlayer;", "getPlayer", "()Lcom/google/android/exoplayer2/SimpleExoPlayer;", "setPlayer", "(Lcom/google/android/exoplayer2/SimpleExoPlayer;)V", "getItemCount", "getItemId", "", "position", "getItemViewType", "onBindViewHolder", "", "holder", "onCreateViewHolder", "view", "Landroid/view/ViewGroup;", "viewType", "onViewRecycled", "ListItemViewHolder", "PhotoItemViewHolder", "VideoItemViewHolder", "app_debug"})
public final class ListItemArrayAdapter extends android.support.v7.widget.RecyclerView.Adapter<com.imnotout.flexo.ListItemArrayAdapter.ListItemViewHolder> {
    private final int orientation = 0;
    @org.jetbrains.annotations.NotNull()
    public com.google.android.exoplayer2.SimpleExoPlayer player;
    @org.jetbrains.annotations.NotNull()
    private final com.google.android.exoplayer2.upstream.DataSource.Factory dataSourceFactory = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context cntx = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.imnotout.flexo.Models.MediaItem> collection = null;
    
    public final int getOrientation() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.exoplayer2.SimpleExoPlayer getPlayer() {
        return null;
    }
    
    public final void setPlayer(@org.jetbrains.annotations.NotNull()
    com.google.android.exoplayer2.SimpleExoPlayer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.exoplayer2.upstream.DataSource.Factory getDataSourceFactory() {
        return null;
    }
    
    @java.lang.Override()
    public long getItemId(int position) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.imnotout.flexo.ListItemArrayAdapter.ListItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup view, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.imnotout.flexo.ListItemArrayAdapter.ListItemViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public void onViewRecycled(@org.jetbrains.annotations.NotNull()
    com.imnotout.flexo.ListItemArrayAdapter.ListItemViewHolder holder) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getCntx() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.imnotout.flexo.Models.MediaItem> getCollection() {
        return null;
    }
    
    public ListItemArrayAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context cntx, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.imnotout.flexo.Models.MediaItem> collection) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00a6\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\r"}, d2 = {"Lcom/imnotout/flexo/ListItemArrayAdapter$ListItemViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "viewType", "", "(Lcom/imnotout/flexo/ListItemArrayAdapter;Landroid/view/View;I)V", "getView", "()Landroid/view/View;", "bind", "", "item", "Lcom/imnotout/flexo/Models/MediaItem;", "app_debug"})
    public abstract class ListItemViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.view.View view = null;
        
        public abstract void bind(@org.jetbrains.annotations.NotNull()
        com.imnotout.flexo.Models.MediaItem item);
        
        @org.jetbrains.annotations.NotNull()
        public android.view.View getView() {
            return null;
        }
        
        public ListItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view, int viewType) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000e"}, d2 = {"Lcom/imnotout/flexo/ListItemArrayAdapter$PhotoItemViewHolder;", "Lcom/imnotout/flexo/ListItemArrayAdapter$ListItemViewHolder;", "Lcom/imnotout/flexo/ListItemArrayAdapter;", "view", "Landroid/view/View;", "viewType", "", "(Lcom/imnotout/flexo/ListItemArrayAdapter;Landroid/view/View;I)V", "getView", "()Landroid/view/View;", "bind", "", "item", "Lcom/imnotout/flexo/Models/MediaItem;", "app_debug"})
    public final class PhotoItemViewHolder extends com.imnotout.flexo.ListItemArrayAdapter.ListItemViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.view.View view = null;
        
        @java.lang.Override()
        public void bind(@org.jetbrains.annotations.NotNull()
        com.imnotout.flexo.Models.MediaItem item) {
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public android.view.View getView() {
            return null;
        }
        
        public PhotoItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view, int viewType) {
            super(null, 0);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0006J\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0006J\u0006\u0010\u001d\u001a\u00020\u0017R\u001a\u0010\b\u001a\u00020\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\u001e"}, d2 = {"Lcom/imnotout/flexo/ListItemArrayAdapter$VideoItemViewHolder;", "Lcom/imnotout/flexo/ListItemArrayAdapter$ListItemViewHolder;", "Lcom/imnotout/flexo/ListItemArrayAdapter;", "view", "Landroid/view/View;", "viewType", "", "(Lcom/imnotout/flexo/ListItemArrayAdapter;Landroid/view/View;I)V", "mediaSource", "Lcom/google/android/exoplayer2/source/MediaSource;", "getMediaSource", "()Lcom/google/android/exoplayer2/source/MediaSource;", "setMediaSource", "(Lcom/google/android/exoplayer2/source/MediaSource;)V", "videoItem", "Lcom/imnotout/flexo/Models/VideoItem;", "getVideoItem", "()Lcom/imnotout/flexo/Models/VideoItem;", "setVideoItem", "(Lcom/imnotout/flexo/Models/VideoItem;)V", "getView", "()Landroid/view/View;", "bind", "", "item", "Lcom/imnotout/flexo/Models/MediaItem;", "onViewHolderInFocus", "pos", "onViewHolderOutOfFocus", "onViewRecycled", "app_debug"})
    public final class VideoItemViewHolder extends com.imnotout.flexo.ListItemArrayAdapter.ListItemViewHolder {
        @org.jetbrains.annotations.NotNull()
        public com.imnotout.flexo.Models.VideoItem videoItem;
        @org.jetbrains.annotations.NotNull()
        public com.google.android.exoplayer2.source.MediaSource mediaSource;
        @org.jetbrains.annotations.NotNull()
        private final android.view.View view = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.imnotout.flexo.Models.VideoItem getVideoItem() {
            return null;
        }
        
        public final void setVideoItem(@org.jetbrains.annotations.NotNull()
        com.imnotout.flexo.Models.VideoItem p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.android.exoplayer2.source.MediaSource getMediaSource() {
            return null;
        }
        
        public final void setMediaSource(@org.jetbrains.annotations.NotNull()
        com.google.android.exoplayer2.source.MediaSource p0) {
        }
        
        @java.lang.Override()
        public void bind(@org.jetbrains.annotations.NotNull()
        com.imnotout.flexo.Models.MediaItem item) {
        }
        
        public final void onViewRecycled() {
        }
        
        public final void onViewHolderInFocus(int pos) {
        }
        
        public final void onViewHolderOutOfFocus(int pos) {
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public android.view.View getView() {
            return null;
        }
        
        public VideoItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view, int viewType) {
            super(null, 0);
        }
    }
}