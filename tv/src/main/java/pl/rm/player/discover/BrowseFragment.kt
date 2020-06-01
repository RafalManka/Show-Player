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
import pl.rm.player.discover.cards.CardPresenter
import pl.rm.player.discover.grid.GridItemPresenter
import pl.rm.player.tools.getSupportColor
import pl.rm.player.tools.getSupportDrawable


class BrowseFragment : BrowseSupportFragment() {

    private lateinit var viewModel: CategoriesViewModel

    private val rowAdapter = ArrayObjectAdapter(ListRowPresenter()).apply {
        // Grid
        val header = HeaderItem(0, "Movies")
        val presenter = GridItemPresenter()
        val gridAdapter = ArrayObjectAdapter(presenter)
        gridAdapter.add("ITEM 1")
        gridAdapter.add("ITEM 2")
        gridAdapter.add("ITEM 3")
        add(ListRow(header, gridAdapter))

        // Card presenter
        val cardPresenterHeader = HeaderItem(1, "Tv shows")
        val cardPresenter = CardPresenter()
        val cardRowAdapter = ArrayObjectAdapter(cardPresenter)
        for (i in 0 until 10) {
            val movie = MovieModel(
                "title$i",
                "studio$i",
                "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/ElephantsDream.jpg"
            )
            cardRowAdapter.add(movie)
        }
        add(ListRow(cardPresenterHeader, cardRowAdapter))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        badgeDrawable = getSupportDrawable(R.drawable.img_banner)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        getSupportColor(R.color.backgroundColorSecondary)?.let { brandColor = it }
        getSupportColor(R.color.backgroundColorSecondaryAccent)?.let { searchAffordanceColor = it }
        adapter = rowAdapter
        viewModel.categories.observe(viewLifecycleOwner, Observer {
            //rowAdapter.add(it.rows)
        })
    }

}

@Suppress("unused")
private val List<Category>.rows: List<ListRow>
    get() = mapIndexed { index, category ->
        ListRow(HeaderItem(index.toLong(), category.name), category.videos.embed)
    }

private val List<Item>.embed: ObjectAdapter
    get() {
        val presenter = GridItemPresenter()
        val gridAdapter = ArrayObjectAdapter(presenter)
        for (item in this) {
            gridAdapter.add(
                when (item) {
                    is Movie -> item.title
                }
            )
        }
        return gridAdapter
    }