package com.imnotout.flexo

import android.content.res.Configuration
import android.graphics.Rect
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import com.imnotout.flexo.Models.MediaItem
import com.imnotout.flexo.Models.MediaType
import com.imnotout.flexo.Models.VideoItem
import com.imnotout.torozo.Utils.SpacingItemDecoration
import kotlinx.android.synthetic.main.activity_recycler_view.*
import android.support.v7.widget.RecyclerView
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.util.Util
import android.support.v7.widget.PagerSnapHelper
import android.view.View
import android.view.MotionEvent
import android.view.GestureDetector
import android.support.v4.view.GestureDetectorCompat
import android.util.Log
import com.google.android.exoplayer2.PlaybackParameters
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.Timeline
import com.google.android.exoplayer2.ui.TimeBar


// https://stackoverflow.com/questions/39503595/how-to-find-percentage-of-each-visible-item-in-recycleview
class RecyclerViewActivity : AppCompatActivity() {
    var playbackPosition: Long = 0
    var currentWindow = 0
    var inFocusViewHolderPosition = -1
    private var mVisible: Boolean = false
    lateinit var videoPlayerStateArray: HashMap<Int, Long>
    lateinit var player: SimpleExoPlayer
    lateinit var itemArrayAdapter: ListItemArrayAdapter
    lateinit var mDetector: GestureDetectorCompat

    private val mHideHandler = Handler()
    private val mHideUIRunnable = Runnable { hideUI() }
    private val mShowControlsUIRunnable = Runnable {
        // Delayed display of UI elements
        showControlsUI()
    }
    private val mHideSystemUIRunnable = Runnable { hideSystemUI() }
    private val mPollVideoProgressRunnable = Runnable { updateProgressBar() }
    private val mDelayHideUITouchListener = View.OnTouchListener { _, _ ->
        if (AUTO_HIDE) {
            delayedHideUI(AUTO_HIDE_DELAY_MILLIS)
        }
        false
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

        private val POLL_INTERVAL_MILLIS = 100

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private val UI_ANIMATION_DELAY = 300
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orientation = resources.configuration.orientation
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            // remove title
//            requestWindowFeature(Window.FEATURE_NO_TITLE);
//            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        }
        setContentView(R.layout.activity_recycler_view)

//        val gestureListener = GestureListener()
        val gestureListener = SingleTapUpGestureListener()
        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
         mDetector = GestureDetectorCompat(this, gestureListener)
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(gestureListener)

        window.decorView.run {
//            setOnTouchListener { _, event ->
//                mDetector.onTouchEvent(event)
//            }
            setOnSystemUiVisibilityChangeListener { visibility ->
                // Note that system bars will only be "visible" if none of the
                // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                    // TODO: The system bars are visible. Make any desired
                    // adjustments to your UI, such as showing the action bar or
                    // other navigational controls.
                    mVisible = true
                    showControlsUI()
                    delayedHideUI(AUTO_HIDE_DELAY_MILLIS)
                    mHideHandler.removeCallbacks(mHideSystemUIRunnable)
                } else {
                    // TODO: The system bars are NOT visible. Make any desired
                    // adjustments to your UI, such as hiding the action bar or
                    // other navigational controls.
                    mVisible = false
                    hideControlsUI()
                    // Schedule a runnable to remove the status and navigation bar after a delay
                    mHideHandler.removeCallbacks(mShowControlsUIRunnable)
                }
            }
        }

        val linearLayoutManager: LinearLayoutManager
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // code for portrait mode
            linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        } else {
            // code for landscape mode
            linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            PagerSnapHelper().attachToRecyclerView(list_item)
        }

//         Video and Image Adapter
        val mediaItemArray = arrayListOf(MediaItem(MediaType.PHOTO.value, "https://homepages.cae.wisc.edu/~ece533/images/airplane.png"),
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
            addOnScrollListener(RecyclerViewOnFocusListener())
            setOnTouchListener { _, event ->
                mDetector.onTouchEvent(event)
            }
        }
        exo_play.run {
            setOnClickListener {
                player.playWhenReady = true
                it.visibility = View.GONE
                exo_pause.visibility = View.VISIBLE
            }
            setOnTouchListener(mDelayHideUITouchListener)
        }
        exo_pause.run {
            setOnClickListener {
                player.playWhenReady = false
                it.visibility = View.GONE
                exo_play.visibility = View.VISIBLE
            }
            setOnTouchListener(mDelayHideUITouchListener)
        }
        exo_progress.run {
            addListener(object : TimeBar.OnScrubListener {
                override fun onScrubMove(timeBar: TimeBar?, position: Long) { }

                override fun onScrubStart(timeBar: TimeBar?, position: Long) { }

                override fun onScrubStop(timeBar: TimeBar?, position: Long, canceled: Boolean) {
                    if(!canceled) player.seekTo(position)
                }

            })
            setOnTouchListener(mDelayHideUITouchListener)
        }
//        savedInstanceState?.getInt("inFocusViewHolderPosition")?.let {
//            inFocusViewHolderPosition = it
//        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (mDetector.onTouchEvent(event)) return true
//        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideUI()
    }


    inner class ExoPlayerEventListener : Player.EventListener {
        override fun onSeekProcessed() { }

        override fun onPositionDiscontinuity(reason: Int) { }

        override fun onRepeatModeChanged(repeatMode: Int) { }

        override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) { }

        override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) { }

        override fun onTracksChanged(trackGroups: TrackGroupArray,
                                     trackSelections: TrackSelectionArray) {
        }

        override fun onLoadingChanged(isLoading: Boolean) {
                Log.d(LOG_APP_TAG, "isLoading: " + isLoading)
        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
//            updateProgressBar()
        }

        override fun onPlayerError(error: ExoPlaybackException) {}

        override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {}
    }

    inner class SingleTapUpGestureListener : GestureDetector.SimpleOnGestureListener() {

//        override fun onDown(event: MotionEvent?): Boolean {
//            Log.d(LOG_APP_TAG, "onDown: " + event.toString())
//            return false
//        }
        override fun onSingleTapConfirmed(event: MotionEvent?): Boolean {
            Log.d(LOG_APP_TAG, "onSingleTapConfirmed: " + event.toString())
            toggleUI()
            return true
        }
    }

    inner class RecyclerViewOnFocusListener: RecyclerView.OnScrollListener() {
        val orientation = resources.configuration.orientation
        val visibilityThreshold: Double = 0.85
//                if (orientation == Configuration.ORIENTATION_PORTRAIT) 0.85
//                else 0.65
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
            val layoutManager = list_item.layoutManager as LinearLayoutManager
            val firstPosition = layoutManager.findFirstVisibleItemPosition()
            val lastPosition = layoutManager.findLastVisibleItemPosition()

            val globalVisibleRect = Rect()

            list_item.getGlobalVisibleRect(globalVisibleRect)

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

                    val viewHolder = list_item.findViewHolderForAdapterPosition(pos)
                            as ListItemArrayAdapter.ListItemViewHolder
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
    }

    private fun toggleUI() = if (mVisible)  hideUI() else showUI()

    private fun showUI() {
        // Show System UI first
        showSystemUI()
        mVisible = true

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mHideSystemUIRunnable)
        mHideHandler.removeCallbacks(mShowControlsUIRunnable)
        mHideHandler.postDelayed(mShowControlsUIRunnable, UI_ANIMATION_DELAY.toLong())
    }
    private fun hideUI() {
        // Hide Controls UI first
        hideControlsUI()
        mVisible = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowControlsUIRunnable)
        mHideHandler.removeCallbacks(mHideSystemUIRunnable)
        mHideHandler.postDelayed(mHideSystemUIRunnable, UI_ANIMATION_DELAY.toLong())
    }
    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHideUI(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideUIRunnable)
        mHideHandler.postDelayed(mHideUIRunnable, delayMillis.toLong())
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

    private fun hideControlsUI() {
        supportActionBar?.hide()
        hideViewInFocusControlsUI(inFocusViewHolderPosition)
    }

    private fun showControlsUI() {
        supportActionBar?.show()
        showViewInFocusControlsUI(inFocusViewHolderPosition)
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(this),
                DefaultTrackSelector(), DefaultLoadControl())
        player.playWhenReady = true
        player.seekTo(currentWindow, playbackPosition)
        itemArrayAdapter.player = player
//        player.addListener(ExoPlayerEventListener())
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

//    private fun showVideoControlsUI(pos: Int) {
//        val viewHolder = list_item.findViewHolderForAdapterPosition(pos) as ListItemArrayAdapter.VideoItemViewHolder?
//        viewHolder?.onViewHolderShowControlsUI(pos)
//    }
//
//    private fun hideVideoControlsUI(pos: Int) {
//        val viewHolder = list_item.findViewHolderForAdapterPosition(pos) as ListItemArrayAdapter.VideoItemViewHolder?
//        viewHolder?.onViewHolderHideControlsUI(pos)
//    }

    private fun showViewInFocusControlsUI(pos: Int) {
        lyt_ui_controls.visibility = View.VISIBLE
        updateProgressBar()
    }

    private fun updateProgressBar() {
        exo_progress.run {
            player.run {
                setPosition(currentPosition)
                setBufferedPosition(bufferedPosition)
                setDuration(duration)
            }
        }
        mHideHandler.postDelayed(mPollVideoProgressRunnable, POLL_INTERVAL_MILLIS.toLong())
    }

    private fun hideViewInFocusControlsUI(pos: Int) {
        lyt_ui_controls.visibility = View.GONE
        mHideHandler.removeCallbacks(mPollVideoProgressRunnable)
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
