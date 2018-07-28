package com.imnotout.flexo

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DataSource.Factory
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import okhttp3.OkHttpClient


class MainActivity : AppCompatActivity() {

    lateinit var player: SimpleExoPlayer
    var playbackPosition: Long = 0
    var currentWindow = 0
    var playWhenReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_recyclerview.setOnClickListener {
            val recyclerViewIntent = Intent(it.context, RecyclerViewActivity::class.java)
            it.context.startActivity(recyclerViewIntent)
        }
        btn_simple_recyclerview.setOnClickListener {
            val recyclerViewIntent = Intent(it.context, FullscreenActivity::class.java)
            it.context.startActivity(recyclerViewIntent)
        }
//        btn_simple_recyclerview.setOnClickListener {
//            val recyclerViewIntent = Intent(it.context, SimpleRecyclerViewActivity::class.java)
//            it.context.startActivity(recyclerViewIntent)
//        }
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(this),
                DefaultTrackSelector(), DefaultLoadControl())

        mov_player_view.setPlayer(player)

        player.setPlayWhenReady(playWhenReady)
        player.seekTo(currentWindow, playbackPosition)

        val uri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4")
        val mediaSource = buildMediaSource(uri)
        player.prepare(mediaSource, true, false)
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
//        return ExtractorMediaSource.Factory(
//                DefaultHttpDataSourceFactory("Flexo-app")).createMediaSource(uri)
//        val dataSourceFactory: DataSource.Factory = object : Factory {
//            override fun createDataSource(): DataSource {
//                return AssetDataSource(this@MainActivity)
//            }
//        }
        val userAgent = "Flexo-app"
        val okHttpClient = OkHttpClient.Builder().build()
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(this@MainActivity,
                null, OkHttpDataSourceFactory(okHttpClient, userAgent, null))
        return ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }

    private fun releasePlayer() {
        player?.run {
            playbackPosition = getCurrentPosition()
            currentWindow = getCurrentWindowIndex()
            playWhenReady = getPlayWhenReady()
            release()
        }
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
}
