package com.imnotout.flexo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0002J\u0012\u0010\'\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\b\u0010*\u001a\u00020&H\u0016J\b\u0010+\u001a\u00020&H\u0016J\u0012\u0010,\u001a\u00020&2\b\u0010-\u001a\u0004\u0018\u00010)H\u0014J\b\u0010.\u001a\u00020&H\u0016J\b\u0010/\u001a\u00020&H\u0016J\b\u00100\u001a\u00020&H\u0002J\u0010\u00101\u001a\u00020&2\u0006\u00102\u001a\u00020\u0004H\u0002J\u0010\u00103\u001a\u00020&2\u0006\u00102\u001a\u00020\u0004H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR6\u0010\u001e\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00130\u001fj\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0013` X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u00a8\u00064"}, d2 = {"Lcom/imnotout/flexo/RecyclerViewActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "currentWindow", "", "getCurrentWindow", "()I", "setCurrentWindow", "(I)V", "inFocusViewHolderPosition", "getInFocusViewHolderPosition", "setInFocusViewHolderPosition", "itemArrayAdapter", "Lcom/imnotout/flexo/ListItemArrayAdapter;", "getItemArrayAdapter", "()Lcom/imnotout/flexo/ListItemArrayAdapter;", "setItemArrayAdapter", "(Lcom/imnotout/flexo/ListItemArrayAdapter;)V", "playbackPosition", "", "getPlaybackPosition", "()J", "setPlaybackPosition", "(J)V", "player", "Lcom/google/android/exoplayer2/SimpleExoPlayer;", "getPlayer", "()Lcom/google/android/exoplayer2/SimpleExoPlayer;", "setPlayer", "(Lcom/google/android/exoplayer2/SimpleExoPlayer;)V", "videoPlayerStateArray", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getVideoPlayerStateArray", "()Ljava/util/HashMap;", "setVideoPlayerStateArray", "(Ljava/util/HashMap;)V", "initializePlayer", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onSaveInstanceState", "outState", "onStart", "onStop", "releasePlayer", "setViewHolderInFocus", "pos", "setViewHolderOutOfFocus", "app_debug"})
public final class RecyclerViewActivity extends android.support.v7.app.AppCompatActivity {
    private long playbackPosition;
    private int currentWindow;
    private int inFocusViewHolderPosition;
    @org.jetbrains.annotations.NotNull()
    public java.util.HashMap<java.lang.Integer, java.lang.Long> videoPlayerStateArray;
    @org.jetbrains.annotations.NotNull()
    public com.google.android.exoplayer2.SimpleExoPlayer player;
    @org.jetbrains.annotations.NotNull()
    public com.imnotout.flexo.ListItemArrayAdapter itemArrayAdapter;
    private java.util.HashMap _$_findViewCache;
    
    public final long getPlaybackPosition() {
        return 0L;
    }
    
    public final void setPlaybackPosition(long p0) {
    }
    
    public final int getCurrentWindow() {
        return 0;
    }
    
    public final void setCurrentWindow(int p0) {
    }
    
    public final int getInFocusViewHolderPosition() {
        return 0;
    }
    
    public final void setInFocusViewHolderPosition(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<java.lang.Integer, java.lang.Long> getVideoPlayerStateArray() {
        return null;
    }
    
    public final void setVideoPlayerStateArray(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.Integer, java.lang.Long> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.exoplayer2.SimpleExoPlayer getPlayer() {
        return null;
    }
    
    public final void setPlayer(@org.jetbrains.annotations.NotNull()
    com.google.android.exoplayer2.SimpleExoPlayer p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.imnotout.flexo.ListItemArrayAdapter getItemArrayAdapter() {
        return null;
    }
    
    public final void setItemArrayAdapter(@org.jetbrains.annotations.NotNull()
    com.imnotout.flexo.ListItemArrayAdapter p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializePlayer() {
    }
    
    private final void setViewHolderInFocus(int pos) {
    }
    
    private final void setViewHolderOutOfFocus(int pos) {
    }
    
    private final void releasePlayer() {
    }
    
    @java.lang.Override()
    protected void onSaveInstanceState(@org.jetbrains.annotations.Nullable()
    android.os.Bundle outState) {
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onPause() {
    }
    
    @java.lang.Override()
    public void onStop() {
    }
    
    public RecyclerViewActivity() {
        super();
    }
}