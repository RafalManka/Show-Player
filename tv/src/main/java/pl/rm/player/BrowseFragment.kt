package pl.rm.player

import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment


class BrowseFragment : BrowseSupportFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        title = getString(R.string.discover_browse_title)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        getSupportColor(R.color.backgroundColorSecondary)?.let { brandColor = it }
        getSupportColor(R.color.backgroundColorSecondaryAccent)?.let { searchAffordanceColor = it }
    }
}
