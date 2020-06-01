package pl.rm.app.ui.adapter

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import pl.rm.app.tools.containsIgnoreCase
import pl.rm.app.ui.Movie
import pl.rm.app.ui.SearchItem

class SearchAdapter : RecyclerView.Adapter<SearchItemViewHolder>(),
    Filterable {

    override fun getFilter(): Filter {
        return filter
    }

    private var filteredItems: List<SearchItem> = emptyList()
    var items: List<SearchItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder =
        SearchItemViewHolder(parent)

    override fun getItemCount(): Int = filteredItems.size

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) =
        holder.bind(filteredItems[position])

    private var filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = mutableListOf<SearchItem>()
            results.addAll(when {
                constraint == null || constraint.length < 3 -> items
                else -> items.filter {
                    when (it) {
                        is Movie -> it.name.containsIgnoreCase(constraint.toString())
                    }
                }
            })
            return FilterResults().also { it.values = results }

        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredItems = (results?.values as? List<*>)
                ?.mapNotNull { it as? SearchItem }
                ?: emptyList()
            notifyDataSetChanged()
        }

    }
}