package pl.rm.app.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.rm.app.R
import pl.rm.app.tools.visible
import pl.rm.app.ui.adapter.SearchAdapter
import pl.rm.core.state.CategoriesViewModel
import pl.rm.core.state.Category
import pl.rm.core.state.Media


class SearchActivity : AppCompatActivity() {

    val progressBar: View by lazy { findViewById<View>(R.id.progressBar) }
    val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    lateinit var viewModel: CategoriesViewModel

    private val adapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        progressBar.visible = true
        viewModel.categories.observe(this, Observer { categories ->
            adapter.items = categories.flatten().map { it.model }
            adapter.filter.filter(null)
            progressBar.visible = false
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        menu?.findItem(R.id.action_search)
            ?.actionView
            ?.let { it as SearchView }
            ?.also { it.imeOptions = EditorInfo.IME_ACTION_DONE }
            ?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            })
        return true
    }
}

private val Media.model: SearchItem
    get() = Movie(title)

private fun List<Category>.flatten(): List<Media> {
    return fold(mutableListOf()) { acc, next ->
        acc.addAll(next.videos)
        acc
    }
}
