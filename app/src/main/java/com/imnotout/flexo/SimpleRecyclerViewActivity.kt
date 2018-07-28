package com.imnotout.flexo

import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.imnotout.flexo.Models.MediaItem
import com.imnotout.flexo.Models.MediaType
import com.imnotout.flexo.Models.VideoItem
import com.imnotout.torozo.Utils.SpacingItemDecoration
import kotlinx.android.synthetic.main.activity_recycler_view.*

class SimpleRecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_recycler_view)

        val simpleItemArray = arrayListOf("1", "2", "3", "4", "5", "6", "7")
        val simpleItemArrayAdapter = SimpleItemArrayAdapter(simpleItemArray)
        list_item.run {
            val visibilityThreshold = 0.85
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = simpleItemArrayAdapter
            addItemDecoration(SpacingItemDecoration(10, SpacingItemDecoration.vertical))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
//                    super.onScrollStateChanged(recyclerView, newState)
//
//                    when(newState) {
//                        RecyclerView.SCROLL_STATE_DRAGGING -> {}
//                        RecyclerView.SCROLL_STATE_IDLE -> {}
//                        RecyclerView.SCROLL_STATE_SETTLING -> {}
//                    }
//
//                }

                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    val layoutManager = layoutManager as LinearLayoutManager
                    val firstPosition = layoutManager.findFirstVisibleItemPosition()
                    val lastPosition = layoutManager.findLastVisibleItemPosition()

                    val globalVisibleRect = Rect()

                    getGlobalVisibleRect(globalVisibleRect)

                    for (pos in firstPosition..lastPosition) {
                        val view = layoutManager.findViewByPosition(pos)
                        val itemVisibleRect = Rect()
                        if (view != null && view.height > 0 && view.getGlobalVisibleRect(itemVisibleRect)) {
                            val visibilityExtent = if (itemVisibleRect.bottom >= globalVisibleRect.bottom) {
                                val visibleHeight = globalVisibleRect.bottom - itemVisibleRect.top
                                Math.min(visibleHeight.toFloat() / view.height, 1f)
                            } else {
                                val visibleHeight = itemVisibleRect.bottom - globalVisibleRect.top
                                Math.min(visibleHeight.toFloat() / view.height, 1f)
                            }
                            simpleItemArray[pos] = visibilityExtent.toString()
                            simpleItemArrayAdapter.notifyItemChanged(pos)
                        }
                    }
                }
            })
        }
    }
}
