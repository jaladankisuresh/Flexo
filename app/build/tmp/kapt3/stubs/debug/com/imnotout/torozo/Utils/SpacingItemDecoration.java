package com.imnotout.torozo.Utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J.\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0014"}, d2 = {"Lcom/imnotout/torozo/Utils/SpacingItemDecoration;", "Landroid/support/v7/widget/RecyclerView$ItemDecoration;", "spacing", "", "displayMode", "(II)V", "getDisplayMode", "()I", "getSpacing", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroid/support/v7/widget/RecyclerView;", "state", "Landroid/support/v7/widget/RecyclerView$State;", "Companion", "app_debug"})
public final class SpacingItemDecoration extends android.support.v7.widget.RecyclerView.ItemDecoration {
    private final int spacing = 0;
    private final int displayMode = 0;
    private static final int horizontal = 0;
    private static final int vertical = 1;
    private static final int gridLayout = 2;
    public static final com.imnotout.torozo.Utils.SpacingItemDecoration.Companion Companion = null;
    
    @java.lang.Override()
    public void getItemOffsets(@org.jetbrains.annotations.Nullable()
    android.graphics.Rect outRect, @org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.NotNull()
    android.support.v7.widget.RecyclerView parent, @org.jetbrains.annotations.Nullable()
    android.support.v7.widget.RecyclerView.State state) {
    }
    
    public final int getSpacing() {
        return 0;
    }
    
    public final int getDisplayMode() {
        return 0;
    }
    
    public SpacingItemDecoration(int spacing, int displayMode) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 10}, bv = {1, 0, 2}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/imnotout/torozo/Utils/SpacingItemDecoration$Companion;", "", "()V", "gridLayout", "", "getGridLayout", "()I", "horizontal", "getHorizontal", "vertical", "getVertical", "app_debug"})
    public static final class Companion {
        
        public final int getHorizontal() {
            return 0;
        }
        
        public final int getVertical() {
            return 0;
        }
        
        public final int getGridLayout() {
            return 0;
        }
        
        private Companion() {
            super();
        }
    }
}