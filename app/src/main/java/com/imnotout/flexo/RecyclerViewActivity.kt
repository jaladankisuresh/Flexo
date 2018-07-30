package com.imnotout.flexo

import android.content.res.Configuration
import android.graphics.Rect
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import com.imnotout.flexo.Models.MediaItem
import com.imnotout.flexo.Models.MediaType
import com.imnotout.flexo.Models.VideoItem
import com.imnotout.torozo.Utils.SpacingItemDecoration
import kotlinx.android.synthetic.main.activity_recycler_view.*
import android.support.v7.widget.RecyclerView
import android.view.Window
import android.view.WindowManager
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.SnapHelper



// https://stackoverflow.com/questions/39503595/how-to-find-percentage-of-each-visible-item-in-recycleview
class RecyclerViewActivity : AppCompatActivity() {
    var playbackPosition: Long = 0
    var currentWindow = 0
    var inFocusViewHolderPosition = -1
    lateinit var videoPlayerStateArray: HashMap<Int, Long>
    lateinit var player: SimpleExoPlayer
    lateinit var itemArrayAdapter: ListItemArrayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // remove title
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        setContentView(R.layout.activity_recycler_view)

        val visibilityThreshold: Double
        val linearLayoutManager: LinearLayoutManager
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // code for portrait mode
            visibilityThreshold = 0.85
            linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        } else {
            // code for landscape mode
            visibilityThreshold = 0.65
            linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            PagerSnapHelper().attachToRecyclerView(list_item)
        }

//         Video and Image Adapter
        val mediaItemArray = arrayListOf(MediaItem(MediaType.PHOTO.value, "file:///android_asset/1.jpg"),
                VideoItem(MediaType.VIDEO.value, "file:///android_asset/5.jpg", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"),
                MediaItem(MediaType.PHOTO.value, "file:///android_asset/2.jpg"),
                VideoItem(MediaType.VIDEO.value, "file:///android_asset/5.jpg",
                        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"),
                MediaItem(MediaType.PHOTO.value, "file:///android_asset/3.jpg"),
                VideoItem(MediaType.VIDEO.value, "file:///android_asset/5.jpg",
                        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"),
                MediaItem(MediaType.PHOTO.value, "file:///android_asset/4.jpg")
        )
        val videoStateArray = savedInstanceState?.getSerializable("videoPlayerStateArray") as HashMap<Int, Long>?
        inFocusViewHolderPosition = savedInstanceState?.getInt("inFocusViewHolderPosition") ?: -1
        videoPlayerStateArray = videoStateArray ?: hashMapOf()
        itemArrayAdapter = ListItemArrayAdapter(this, mediaItemArray)

        list_item.run {
            layoutManager = linearLayoutManager
            adapter = itemArrayAdapter
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
//                            val visibilityExtent = if (itemVisibleRect.bottom >= globalVisibleRect.bottom) {
//                                val visibleHeight = globalVisibleRect.bottom - itemVisibleRect.top
//                                Math.min(visibleHeight.toFloat() / view.height, 1f)
//                            } else {
//                                val visibleHeight = itemVisibleRect.bottom - globalVisibleRect.top
//                                Math.min(visibleHeight.toFloat() / view.height, 1f)
//                            }
                            val visibleHeight = if (itemVisibleRect.bottom >= globalVisibleRect.bottom)
                                globalVisibleRect.bottom - itemVisibleRect.top
                            else itemVisibleRect.bottom - globalVisibleRect.top
                            val visibleHeightExtent = visibleHeight.toFloat() / view.height

                            val visibleWidth = if (itemVisibleRect.right >= globalVisibleRect.right)
                                globalVisibleRect.right - itemVisibleRect.left
                            else itemVisibleRect.right - globalVisibleRect.left
                            val visibleWidthExtent = visibleWidth.toFloat() / view.width

                            val visibilityExtent = Math.min(visibleHeightExtent * visibleWidthExtent, 1f)

                            val viewHolder = findViewHolderForAdapterPosition(pos) as ListItemArrayAdapter.ListItemViewHolder
//                            viewHolder.notifyVisibility(visibilityExtent.toString())

                            if(viewHolder is ListItemArrayAdapter.VideoItemViewHolder &&
                                    visibilityExtent >=  visibilityThreshold  && inFocusViewHolderPosition != pos
                                && player.playbackState == Player.STATE_IDLE) {
//                                val viewHolder = findViewHolderForAdapterPosition(pos) as ListItemArrayAdapter.ListItemViewHolder
                                inFocusViewHolderPosition = pos
                                setViewHolderInFocus(pos)

                            }
                            else if(viewHolder is ListItemArrayAdapter.VideoItemViewHolder &&
                                    visibilityExtent <  visibilityThreshold  && inFocusViewHolderPosition == pos
                                    && player.playbackState != Player.STATE_IDLE) {
                                inFocusViewHolderPosition = -1
                                setViewHolderOutOfFocus(pos)
                            }
                        }
                    }
                }
            })
        }
        savedInstanceState?.getInt("inFocusViewHolderPosition")?.let {
            inFocusViewHolderPosition = it
        }
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(this),
                DefaultTrackSelector(), DefaultLoadControl())
        player.playWhenReady = true
        player.seekTo(currentWindow, playbackPosition)
        itemArrayAdapter.player = player
//        if(inFocusViewHolderPosition > 0) {
//            list_item.smoothScrollToPosition(inFocusViewHolderPosition)
//            setViewHolderInFocus(inFocusViewHolderPosition)
//        }
    }

    private fun setViewHolderInFocus(pos: Int) {
        val playbackPosition = videoPlayerStateArray.get(pos) ?: 0
        player.seekTo(playbackPosition)
        val viewHolder = list_item.findViewHolderForAdapterPosition(pos) as ListItemArrayAdapter.VideoItemViewHolder
        viewHolder.onViewHolderInFocus(pos)
    }

    private fun setViewHolderOutOfFocus(pos: Int) {
        videoPlayerStateArray.put(pos, player.currentPosition)
        val viewHolder = list_item.findViewHolderForAdapterPosition(pos) as ListItemArrayAdapter.VideoItemViewHolder
        viewHolder.onViewHolderOutOfFocus(pos, player.currentPosition)
    }

    private fun releasePlayer() {
//        if(inFocusViewHolderPosition > 0) setViewHolderOutOfFocus(inFocusViewHolderPosition)

        player.run {
            if(playbackState != Player.STATE_IDLE)
            playbackPosition = currentPosition
            currentWindow = currentWindowIndex
            release()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putSerializable("videoPlayerStateArray", videoPlayerStateArray)
        if(inFocusViewHolderPosition > 0) outState?.putInt("inFocusViewHolderPosition", inFocusViewHolderPosition)
    }


    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23) {
            initializePlayer()
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }
}
