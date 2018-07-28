package com.imnotout.torozo.Utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


class SpacingItemDecoration(val spacing:Int ,val displayMode:Int):RecyclerView.ItemDecoration() {

    companion object {
        val horizontal = 0
        val vertical = 1
        val gridLayout = 2
    }


    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state!!.itemCount
        val layoutManager:RecyclerView.LayoutManager = parent.layoutManager

        if(parent.getItemDecorationAt(0).equals(this))
             when (displayMode) {
                horizontal -> if(position !=0) outRect?.set(spacing,0,0,0)
                vertical -> outRect?.set(0,0,0,spacing)
                gridLayout -> outRect?.set(0,0,spacing,spacing)
                else -> outRect?.set(0,0,0,0)
             }
    }
}
