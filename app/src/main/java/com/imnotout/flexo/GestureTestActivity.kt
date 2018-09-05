package com.imnotout.flexo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.util.Log

class GestureTestActivity : AppCompatActivity(),
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private lateinit var mDetector: GestureDetectorCompat

    // Called when the activity is first created.
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture_test)
        title = "GestureTestActivity"
        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = GestureDetectorCompat(this, this)
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (this.mDetector.onTouchEvent(event)) {
            true
        } else super.onTouchEvent(event)
    }

    override fun onDown(event: MotionEvent): Boolean {
        Log.d(LOG_APP_TAG, "onDown: " + event.toString())
        return true
    }

    override fun onFling(event1: MotionEvent, event2: MotionEvent,
                         velocityX: Float, velocityY: Float): Boolean {
        Log.d(LOG_APP_TAG, "onFling: " + event1.toString() + event2.toString())
        return true
    }

    override fun onLongPress(event: MotionEvent) {
        Log.d(LOG_APP_TAG, "onLongPress: " + event.toString())
    }

    override fun onScroll(event1: MotionEvent, event2: MotionEvent, distanceX: Float,
                          distanceY: Float): Boolean {
        Log.d(LOG_APP_TAG, "onScroll: " + event1.toString() + event2.toString())
        return true
    }

    override fun onShowPress(event: MotionEvent) {
        Log.d(LOG_APP_TAG, "onShowPress: " + event.toString())
    }

    override fun onSingleTapUp(event: MotionEvent): Boolean {
        Log.d(LOG_APP_TAG, "onSingleTapUp: " + event.toString())
        return true
    }

    override fun onDoubleTap(event: MotionEvent): Boolean {
        Log.d(LOG_APP_TAG, "onDoubleTap: " + event.toString())
        return true
    }

    override fun onDoubleTapEvent(event: MotionEvent): Boolean {
        Log.d(LOG_APP_TAG, "onDoubleTapEvent: " + event.toString())
        return true
    }

    override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
        Log.d(LOG_APP_TAG, "onSingleTapConfirmed: " + event.toString())
        return true
    }
}
