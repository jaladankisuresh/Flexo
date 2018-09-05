package com.imnotout.flexo

import android.app.Application
import java.io.File

const val LOG_APP_TAG = "Flexo_App"
class AndroidApplication : Application() {

    lateinit var mediaVideoPath: File
    lateinit var mediaImagePath: File
    companion object {
        const val MEDIA_VIDEOS = "Movies"
        const val MEDIA_IMAGES = "Pictures"
    }
    override fun onCreate() {
        super.onCreate()

        mediaVideoPath = File(applicationContext.getFilesDir(), MEDIA_VIDEOS)
        mediaVideoPath.mkdir()
        mediaImagePath = File(applicationContext.getFilesDir(), MEDIA_IMAGES)
        mediaImagePath.mkdir()
    }

    override fun onTerminate() {
        mediaVideoPath.delete()
        mediaImagePath.delete()

        super.onTerminate()
    }
}