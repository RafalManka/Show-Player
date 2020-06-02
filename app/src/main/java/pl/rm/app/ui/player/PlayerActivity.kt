package pl.rm.app.ui.player

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import pl.rm.app.R
import pl.rm.app.tools.extensions.uri
import java.util.*


fun Context.newPlayerActivityIntent(title: String, mediaUrl: String): Intent =
    Intent(this, PlayerActivity::class.java)
        .apply {
            putExtra("mediaUrl", mediaUrl)
            putExtra("title", title)
        }

class PlayerActivity : AppCompatActivity() {
    private var isControllerVisible = true
    private lateinit var player: SimpleExoPlayer
    private val videoView: SimpleExoPlayerView by lazy { findViewById<SimpleExoPlayerView>(R.id.ep_video_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        title = intent.getStringExtra("title")?.toUpperCase(Locale.US)
        videoView.setControllerVisibilityListener {
            isControllerVisible = it == View.VISIBLE
            if (it == View.VISIBLE) {
                showActionBar(true)
            } else {
                showActionBar(false)
            }
        }
    }

    private fun showActionBar(show: Boolean) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (show) {
                supportActionBar?.show()
            } else {
                supportActionBar?.hide()
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        if (isPortrait) {
            supportActionBar?.show()
        } else {
            showActionBar(isControllerVisible)
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            val meter = DefaultBandwidthMeter()
            val selector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(meter))
            player = ExoPlayerFactory.newSimpleInstance(this, selector)
            val uri = intent.getStringExtra("mediaUrl")?.uri
            val source = DefaultHttpDataSourceFactory("exoplayer_video")
            val extractor = DefaultExtractorsFactory()
            val mediaSource = ExtractorMediaSource.Factory(source)
                .setExtractorsFactory(extractor)
                .createMediaSource(uri)
            videoView.player = player
            player.prepare(mediaSource)
            player.playWhenReady = true
        } catch (e: Exception) {
            Log.e("Player", "ExoPlayer Error", e)
        }
    }

    override fun onPause() {
        super.onPause()
        player.stop()
        player.release()
    }
}
