package pl.rm.player.discover

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
import androidx.leanback.app.BackgroundManager
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import pl.rm.player.R
import java.util.*

private const val BACKGROUND_UPDATE_DELAY = 500
private const val DEFAULT_BACKGROUND_RES_ID = R.drawable.default_background

class DiscoverBackgroundLoader(activity: Activity?) {

    private val backgroundManager = BackgroundManager.getInstance(activity)
    private var defaultBackground: Drawable? = activity?.getDrawable(DEFAULT_BACKGROUND_RES_ID)
    private val handler = Handler(Looper.getMainLooper())
    private val metrics: DisplayMetrics
    private var backgroundURI: String? = null
    private var backgroundTimer: Timer? = null

    private var target: Target = object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, loadedFrom: Picasso.LoadedFrom?) {
            backgroundManager.setBitmap(bitmap)
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            backgroundManager.drawable = errorDrawable
        }

        override fun onPrepareLoad(drawable: Drawable?) {}

        override fun hashCode(): Int {
            return backgroundManager.hashCode()
        }
    }

    private inner class UpdateBackgroundTask : TimerTask() {
        override fun run() {
            handler.post { updateBackground(backgroundURI) }
        }
    }

    init {
        backgroundManager.attach(activity?.window)
        metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
    }

    fun updateBackgroundWithDelay(uri: String) {
        backgroundURI = uri
        if (backgroundTimer != null) {
            backgroundTimer?.cancel()
        }
        backgroundTimer = Timer()
        backgroundTimer?.schedule(UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY.toLong())
    }

    private fun updateBackground(uri: String?) {
        try {
            val background = defaultBackground ?: return
            val realUri = uri ?: return
            Picasso.get()
                .load(realUri)
                .resize(metrics.widthPixels, metrics.heightPixels)
                .centerCrop()
                .error(background)
                .into(target)
        } catch (e: Exception) {
            Log.e("", e.toString())
        }
    }

}
