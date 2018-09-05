package com.imnotout.flexo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.GestureDetector
import android.text.method.Touch.onTouchEvent
import android.support.v4.view.GestureDetectorCompat
import android.util.Log

class GestureTest2Activity : AppCompatActivity() {

    private lateinit var mDetector: GestureDetectorCompat

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesture_test2)
        title = "GestureTest2Activity"
        mDetector = GestureDetectorCompat(this, MyGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        this.mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    internal inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(event: MotionEvent): Boolean {
            Log.d(LOG_APP_TAG, "onDown: " + event.toString())
            return true
        }

        override fun onFling(event1: MotionEvent, event2: MotionEvent,
                             velocityX: Float, velocityY: Float): Boolean {
            Log.d(LOG_APP_TAG, "onFling: " + event1.toString() + event2.toString())
            return true
        }

        override fun onSingleTapUp(event: MotionEvent): Boolean {
            Log.d(LOG_APP_TAG, "onSingleTapUp: " + event.toString())
            return true
        }

        override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
            Log.d(LOG_APP_TAG, "onSingleTapConfirmed: " + event.toString())
            return true
        }
    }
}