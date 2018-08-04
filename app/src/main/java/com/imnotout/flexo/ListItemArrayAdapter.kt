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
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.photo_list_item.view.*
import kotlinx.android.synthetic.main.video_list_item.view.*
import android.content.Intent
import android.graphics.Bitmap
import android.support.v4.content.FileProvider
import android.view.TextureView
import android.widget.Toast
import com.imnotout.flexo.AndroidApplication.Companion.MEDIA_IMAGES
import com.imnotout.flexo.AndroidApplication.Companion.MEDIA_VIDEOS
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.UI
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class ListItemArrayAdapter(val cntx: Context, val collection: List<MediaItem>) :
        RecyclerView.Adapter<ListItemArrayAdapter.ListItemViewHolder>() {
    val orientation = cntx.resources.configuration.orientation
//    val userAgent = "Flexo-app"
//    val okHttpClient = OkHttpClient.Builder().build()
    lateinit var player: SimpleExoPlayer
    val videoPauseBitmapArray = HashMap<Int, Bitmap>()
    val dataSourceFactory: DataSource.Factory = CacheDataSourceFactory(cntx,
            100 * 1024 * 1024, 5 * 1024 * 1024)

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

    @Throws(IOException::class)
    private fun copyFile(src: File, dst: File) {
        FileInputStream(src).use({ inStream  ->
            FileOutputStream(dst).use({ outStream ->
                // Transfer bytes from inStream to outStream
                val inChannel = inStream.channel
                val outChannel = outStream.channel
                inChannel.transferTo(0, inChannel.size(), outChannel)
            })
        })
    }

    @Throws(IOException::class)
    private fun createMediaFile(cntx: Context, type: MediaType): File {
        // Create an image file name
        val imageFileName = "media.flexo.imnotout.com"
        val imageFile = when(type) {
            MediaType.VIDEO -> {
                val mediaPath = File(cntx.getFilesDir(), MEDIA_VIDEOS)
                File.createTempFile(imageFileName, ".mp4", mediaPath)
            }
            else -> {
                val mediaPath = File(cntx.getFilesDir(), MEDIA_IMAGES)
                File.createTempFile(imageFileName, ".jpg", mediaPath)
            }
        }
        return imageFile
//    return FileProvider.getUriForFile(cntx, "com.imnotout.imageresizer.fileprovider", imageFile)
    }

    private fun sendMedia(cntx: Context, uri: Uri) {
        val sendIntent = Intent()
        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_STREAM, uri)
        sendIntent.setType("image/jpeg")
        cntx.startActivity(sendIntent)
    }

    inner abstract class ListItemViewHolder(open val view: View, viewType: Int) : RecyclerView.ViewHolder(view) {
        abstract fun bind(position: Int, item: MediaItem)
//        fun notifyVisibility(ratio: String) {
//            view.findViewById<TextView>(R.id.txt_view).text = ratio
//        }
    }

    inner class PhotoItemViewHolder(override val view: View, viewType: Int): ListItemViewHolder(view, viewType) {
        override fun bind(position: Int, item: MediaItem) {
            view.run {
//                https://github.com/bumptech/glide/issues/459
                setOnLongClickListener {
                    async(UI) {
                        val mediaInFile = async(CommonPool) {
                            GlideApp
                                    .with(context)
                                    .downloadOnly()
                                    .load(item.url)
                                    .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                    .get()
                        }.await()
                        val mediaOutFile = createMediaFile(context, MediaType.PHOTO)
                        copyFile(mediaInFile, mediaOutFile)
                        val imageContentUri = FileProvider.getUriForFile(context, "com.imnotout.flexo.fileprovider",
                                mediaOutFile)
                        sendMedia(context, imageContentUri)
                    }.invokeOnCompletion {
                        it?.printStackTrace()
                    }
                    true
                }
                GlideApp.with(context)
                        .load(item.url)
//                    .centerCrop()
                        .into(img_view)
            }
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
