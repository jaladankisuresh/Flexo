package com.imnotout.flexo

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.okhttp.OkHttpDataSourceFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.imnotout.flexo.Models.MediaItem
import com.imnotout.flexo.Models.MediaType
import com.imnotout.flexo.Models.VideoItem
import com.imnotout.flexo.Utils.CacheDataSourceFactory
import com.imnotout.torozo.Utils.GlideApp
import kotlinx.android.synthetic.main.photo_list_item.view.*
import kotlinx.android.synthetic.main.video_list_item.view.*
import okhttp3.Cache
import okhttp3.OkHttpClient
import android.R.attr.bitmap
import android.graphics.Bitmap
import android.os.Build
import android.media.MediaMetadataRetriever
import android.view.TextureView


class ListItemArrayAdapter(val cntx: Context, val collection: List<MediaItem>) :
        RecyclerView.Adapter<ListItemArrayAdapter.ListItemViewHolder>() {
    val orientation = cntx.resources.configuration.orientation
//    val userAgent = "Flexo-app"
//    val okHttpClient = OkHttpClient.Builder().build()
    lateinit var player: SimpleExoPlayer
    val videoPauseBitmapArray = HashMap<Int, Bitmap>()
    val dataSourceFactory: DataSource.Factory = CacheDataSourceFactory(cntx,
            100 * 1024 * 1024, 5 * 1024 * 1024)
//    val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(cntx,
//            null, OkHttpDataSourceFactory(okHttpClient, userAgent, null))

//    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//    TrackSelection.Factory videoTrackSelectionFactory =
//    new AdaptiveTrackSelection.Factory(bandwidthMeter);
//    TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
//
//    SimpleExoPlayer exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
//    MediaSource audioSource = new ExtractorMediaSource(Uri.parse(url),
//    new CacheDataSourceFactory(context, 100 * 1024 * 1024, 5 * 1024 * 1024), new DefaultExtractorsFactory(), null, null);
//    exoPlayer.setPlayWhenReady(true);
//    exoPlayer.prepare(audioSource);


    override fun getItemId(position: Int): Long = position.toLong()
    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): ListItemViewHolder {
        if(viewType == MediaType.PHOTO.value) {
            val itemView = LayoutInflater.from(view.context).inflate(R.layout.photo_list_item, view, false)
            if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
                itemView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
            }

            return PhotoItemViewHolder(itemView, viewType)
        }
        else {
            val itemView = LayoutInflater.from(view.context).inflate(R.layout.video_list_item, view, false)
            if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
                itemView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
            }

            return VideoItemViewHolder(itemView, viewType)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return collection.get(position).type
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(position, collection.get(position))
    }

    override fun onViewRecycled(holder: ListItemViewHolder) {
        super.onViewRecycled(holder)

        if(holder is VideoItemViewHolder) holder.onViewRecycled()
    }

    override fun getItemCount(): Int {
        return collection.size
    }

    inner abstract class ListItemViewHolder(open val view: View, viewType: Int) : RecyclerView.ViewHolder(view) {
        abstract fun bind(position: Int, item: MediaItem)
//        fun notifyVisibility(ratio: String) {
//            view.findViewById<TextView>(R.id.txt_view).text = ratio
//        }
    }

    inner class PhotoItemViewHolder(override val view: View, viewType: Int): ListItemViewHolder(view, viewType) {
        override fun bind(position: Int, item: MediaItem) {
            GlideApp.with(view.context)
                    .load(item.url)
//                    .centerCrop()
                    .into(view.img_view)
        }
    }
    inner class VideoItemViewHolder(override val view: View, viewType: Int): ListItemViewHolder(view, viewType) {
        lateinit var videoItem: VideoItem
        lateinit var mediaSource: MediaSource

        override fun bind(position: Int, item: MediaItem) {
            videoPauseBitmapArray.get(position)?.let { view.img_thumbnail.setImageBitmap(it) }
            videoItem = item as VideoItem
            mediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(item.videoUrl))
        }
        fun onViewRecycled() {
            mediaSource.releaseSource()
        }
        fun onViewHolderInFocus(pos: Int) {
            if(::player.isInitialized) {
                player.prepare(mediaSource, false, false)
                view.mov_player_view.player = player
                view.img_thumbnail.visibility = View.GONE
                val bitmap = videoPauseBitmapArray.remove(pos)
                bitmap?.recycle()
            }
        }
        fun onViewHolderOutOfFocus(pos: Int, seekTo: Long) {
            if(::player.isInitialized) {
                val textureView = view.mov_player_view.videoSurfaceView as TextureView
                val videoBitmap = textureView.bitmap
                videoPauseBitmapArray.put(pos, videoBitmap)
                view.img_thumbnail.setImageBitmap(videoBitmap)
                player.stop()
                view.mov_player_view.player = null
                player.clearVideoSurface()

//                var mediaMetadataRetriever: MediaMetadataRetriever? = null
//                try {
//                    mediaMetadataRetriever = MediaMetadataRetriever()
//                    mediaMetadataRetriever.setDataSource(videoItem.videoUrl, HashMap<String, String>())
//                    val bitmap = mediaMetadataRetriever.getFrameAtTime(seekTo)
//                    view.img_thumbnail.setImageBitmap(bitmap)
//                } catch (e: Exception) {
//                    e.printStackTrace();
//                    throw Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.message)
//
//                } finally {
//                    mediaMetadataRetriever?.release()
//                }
                view.img_thumbnail.visibility = View.VISIBLE
            }
        }
    }

}
