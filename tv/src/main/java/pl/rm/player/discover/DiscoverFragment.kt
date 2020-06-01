package pl.rm.player.discover

import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.rm.core.state.CategoriesViewModel
import pl.rm.core.state.Category
import pl.rm.core.state.Item
import pl.rm.core.state.Movie
import pl.rm.player.R
import pl.rm.player.discover.cards.DiscoverCardPresenter
import pl.rm.player.tools.byPrependingImageBasePath
import pl.rm.player.tools.getSupportColor
import pl.rm.player.tools.getSupportDrawable


private const val moviesBackgroundUrl =
    "https://s17736.pcdn.co/wp-content/uploads/2019/03/jason-leung-479251-unsplash.jpg"

class BrowseFragment : BrowseSupportFragment() {

    private lateinit var viewModel: CategoriesViewModel
    private lateinit var simpleBackgroundLoader: DiscoverBackgroundLoader

    private val rowAdapter = ArrayObjectAdapter(ListRowPresenter())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        simpleBackgroundLoader = DiscoverBackgroundLoader(activity)
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        badgeDrawable = getSupportDrawable(R.drawable.img_banner)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        getSupportColor(R.color.backgroundColorSecondary)?.let { brandColor = it }
        getSupportColor(R.color.backgroundColorSecondaryAccent)?.let { searchAffordanceColor = it }
        adapter = rowAdapter
        viewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
            categories.rows.forEach { rowAdapter.add(it) }
        })

        onItemViewSelectedListener = ItemViewSelectedListener()
    }

    private inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is DiscoverMovie) {
                simpleBackgroundLoader.updateBackgroundWithDelay(moviesBackgroundUrl)
            }
        }
    }
}

private val List<Category>.rows: List<ListRow>
    get() = mapIndexed { index, category ->
        ListRow(HeaderItem(index.toLong(), category.name), category.videos.embed)
    }

private val List<Item>.embed: ObjectAdapter
    get() {
        val cardPresenter = DiscoverCardPresenter()
        val cardRowAdapter = ArrayObjectAdapter(cardPresenter)
        for (item in this) {
            cardRowAdapter.add(
                when (item) {
                    is Movie -> DiscoverMovie(
                        item.title,
                        item.description,
                        item.thumb.byPrependingImageBasePath
                    )
                }
            )
        }
        return cardRowAdapter
    }