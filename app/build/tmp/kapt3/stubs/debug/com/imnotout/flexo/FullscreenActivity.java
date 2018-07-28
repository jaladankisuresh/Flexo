package com.imnotout.flexo;

import java.lang.System;

/**
 * * An example full-screen activity that shows and hides the system UI (i.e.
 * * status bar and navigation/system bar) with user interaction.
 */
@kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 F2\u00020\u0001:\u0001FB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0004H\u0002J\b\u00102\u001a\u000200H\u0002J\b\u00103\u001a\u000200H\u0002J\u0012\u00104\u001a\u0002002\b\u00105\u001a\u0004\u0018\u000106H\u0014J\b\u00107\u001a\u000200H\u0016J\u0012\u00108\u001a\u0002002\b\u00105\u001a\u0004\u0018\u000106H\u0014J\b\u00109\u001a\u000200H\u0016J\u001c\u0010:\u001a\u0002002\b\u0010;\u001a\u0004\u0018\u0001062\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\b\u0010>\u001a\u000200H\u0016J\b\u0010?\u001a\u000200H\u0016J\b\u0010@\u001a\u000200H\u0002J\u0010\u0010A\u001a\u0002002\u0006\u0010B\u001a\u00020\u0004H\u0002J\u0010\u0010C\u001a\u0002002\u0006\u0010B\u001a\u00020\u0004H\u0002J\b\u0010D\u001a\u000200H\u0002J\b\u0010E\u001a\u000200H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'R6\u0010(\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d0)j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001d`*X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\u00a8\u0006G"}, d2 = {"Lcom/imnotout/flexo/FullscreenActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "currentWindow", "", "getCurrentWindow", "()I", "setCurrentWindow", "(I)V", "inFocusViewHolderPosition", "getInFocusViewHolderPosition", "setInFocusViewHolderPosition", "itemArrayAdapter", "Lcom/imnotout/flexo/ListItemArrayAdapter;", "getItemArrayAdapter", "()Lcom/imnotout/flexo/ListItemArrayAdapter;", "setItemArrayAdapter", "(Lcom/imnotout/flexo/ListItemArrayAdapter;)V", "mDelayHideTouchListener", "Landroid/view/View$OnTouchListener;", "mHideHandler", "Landroid/os/Handler;", "mHidePart2Runnable", "Ljava/lang/Runnable;", "mHideRunnable", "mShowPart2Runnable", "mVisible", "", "playbackPosition", "", "getPlaybackPosition", "()J", "setPlaybackPosition", "(J)V", "player", "Lcom/google/android/exoplayer2/SimpleExoPlayer;", "getPlayer", "()Lcom/google/android/exoplayer2/SimpleExoPlayer;", "setPlayer", "(Lcom/google/android/exoplayer2/SimpleExoPlayer;)V", "videoPlayerStateArray", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getVideoPlayerStateArray", "()Ljava/util/HashMap;", "setVideoPlayerStateArray", "(Ljava/util/HashMap;)V", "delayedHide", "", "delayMillis", "hide", "initializePlayer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onPostCreate", "onResume", "onSaveInstanceState", "outState", "outPersistentState", "Landroid/os/PersistableBundle;", "onStart", "onStop", "releasePlayer", "setViewHolderInFocus", "pos", "setViewHolderOutOfFocus", "show", "toggle", "Companion", "app_debug"})
public final class FullscreenActivity extends android.support.v7.app.AppCompatActivity {
    private final android.os.Handler mHideHandler = null;
    private final java.lang.Runnable mHidePart2Runnable = null;
    private final java.lang.Runnable mShowPart2Runnable = null;
    private boolean mVisible;
    private final java.lang.Runnable mHideRunnable = null;
    
    /**
     * * Touch listener to use for in-layout UI controls to delay hiding the
     *     * system UI. This is to prevent the jarring behavior of controls going away
     *     * while interacting with activity UI.
     */
    private final android.view.View.OnTouchListener mDelayHideTouchListener = null;
    private long playbackPosition;
    private int currentWindow;
    private int inFocusViewHolderPosition;
    @org.jetbrains.annotations.NotNull()
    public java.util.HashMap<java.lang.Integer, java.lang.Long> videoPlayerStateArray;
    @org.jetbrains.annotations.NotNull()
    public com.google.android.exoplayer2.SimpleExoPlayer player;
    @org.jetbrains.annotations.NotNull()
    public com.imnotout.flexo.ListItemArrayAdapter itemArrayAdapter;
    
    /**
     * * Whether or not the system UI should be auto-hidden after
     *         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
     */
    private static final boolean AUTO_HIDE = true;
    
    /**
     * * If [AUTO_HIDE] is set, the number of milliseconds to wait after
     *         * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    
    /**
     * * Some older devices needs a small delay between UI widget updates
     *         * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    public static final com.imnotout.flexo.FullscreenActivity.Companion Companion = null;
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
    
    @java.lang.Override()
    protected void onPostCreate(@org.jetbrains.annotations.Nullable()
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
    public void onSaveInstanceState(@org.jetbrains.annotations.Nullable()
    android.os.Bundle outState, @org.jetbrains.annotations.Nullable()
    android.os.PersistableBundle outPersistentState) {
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
    
    private final void toggle() {
    }
    
    private final void hide() {
    }
    
    private final void show() {
    }
    
    /**
     * * Schedules a call to hide() in [delayMillis], canceling any
     *     * previously scheduled calls.
     */
    private final void delayedHide(int delayMillis) {
    }
    
    public FullscreenActivity() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/imnotout/flexo/FullscreenActivity$Companion;", "", "()V", "AUTO_HIDE", "", "AUTO_HIDE_DELAY_MILLIS", "", "UI_ANIMATION_DELAY", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}