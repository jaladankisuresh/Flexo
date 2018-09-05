package com.imnotout.flexo

import android.content.res.Configuration
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.Util
import com.imnotout.flexo.Models.MediaItem
import com.imnotout.flexo.Models.MediaType
import com.imnotout.flexo.Models.VideoItem
import com.imnotout.torozo.Utils.SpacingItemDecoration
import kotlinx.android.synthetic.main.activity_fullscreen.*

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class FullscreenActivity : AppCompatActivity() {
    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        fullscreen_content.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
    private val mShowPart2Runnable = Runnable {
        // Delayed display of UI elements
        supportActionBar?.show()
        fullscreen_content_controls.visibility = View.VISIBLE
    }
    private var mVisible: Boolean = false
    private val mHideRunnable = Runnable { hide() }
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val mDelayHideTouchListener = View.OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            delayedHide(AUTO_HIDE_DELAY_MILLIS)
        }
        false
    }

    var playbackPosition: Long = 0
    var currentWindow = 0
    var inFocusViewHolderPosition = -1
    lateinit var videoPlayerStateArray: HashMap<Int, Long>
    lateinit var player: SimpleExoPlayer
    lateinit var itemArrayAdapter: ListItemArrayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fullscreen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mVisible = true

        // Set up the user interaction to manually show or hide the system UI.
        fullscreen_content.setOnClickListener {
            toggle()
        }

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        dummy_button.setOnTouchListener(mDelayHideTouchListener)

        val visibilityThreshold: Double
        val linearLayoutManager: LinearLayoutManager
        val orientation = resources.configuration.orientation
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
                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/VolkswagenGTIReview.mp4"),
                MediaItem(MediaType.PHOTO.value, "file:///android_asset/3.jpg"),
                VideoItem(MediaType.VIDEO.value, "file:///android_asset/5.jpg",
                        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"),
                MediaItem(MediaType.PHOTO.value, "file:///android_asset/4.jpg")
        )
        val videoStateArray = savedInstanceState?.getSerializable("videoPlayerStateArray") as HashMap<Int, Long>?
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

                            if(visibilityExtent >=  visibilityThreshold  && inFocusViewHolderPosition != pos
                                    && player.playbackState == Player.STATE_IDLE) {
//                                val viewHolder = findViewHolderForAdapterPosition(pos) as ListItemArrayAdapter.ListItemViewHolder
                                if(viewHolder is ListItemArrayAdapter.VideoItemViewHolder) {
                                    inFocusViewHolderPosition = pos
                                    setViewHolderInFocus(pos)
                                }

                            }
                            else if(visibilityExtent <  visibilityThreshold  && inFocusViewHolderPosition == pos
                                    && player.playbackState != Player.STATE_IDLE) {
                                inFocusViewHolderPosition = -1
                                setViewHolderOutOfFocus(pos)
                            }
                        }
                    }
                }
            })
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }



    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(this),
                DefaultTrackSelector(), DefaultLoadControl())
        player.playWhenReady = true
        player.seekTo(currentWindow, playbackPosition)
        itemArrayAdapter.player = player
        if(inFocusViewHolderPosition > 0) setViewHolderInFocus(inFocusViewHolderPosition)
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
        if(inFocusViewHolderPosition > 0) setViewHolderOutOfFocus(inFocusViewHolderPosition)

        player.run {
            if(playbackState != Player.STATE_IDLE)
                playbackPosition = currentPosition
            currentWindow = currentWindowIndex
            release()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState?.putSerializable("videoPlayerStateArray", videoPlayerStateArray)
    }


    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23 || !this::player.isInitialized) {
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

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        fullscreen_content_controls.visibility = View.GONE
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
        // Show the system bar
        fullscreen_content.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        mVisible = true

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable)
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }
}
