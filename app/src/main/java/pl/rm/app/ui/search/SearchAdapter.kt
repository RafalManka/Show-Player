package pl.rm.app.ui.search

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import pl.rm.app.tools.extensions.containsIgnoreCase


class SearchAdapter(
    private var onclickListener: (Movie) -> Unit
) : RecyclerView.Adapter<SearchItemViewHolder>(), Filterable {

    override fun getFilter(): Filter {
        return filter
    }

    private var filteredItems: List<Movie> = emptyList()
    var items: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder =
        SearchItemViewHolder(parent, onclickListener)

    override fun getItemCount(): Int = filteredItems.size

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) =
        holder.bind(filteredItems[position])

    private var filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = mutableListOf<Movie>()
            results.addAll(when {
                constraint == null || constraint.length < 3 -> items
                else -> items.filter {
                    it.title.containsIgnoreCase(constraint.toString()) || it.subtitle.containsIgnoreCase(
                        constraint.toString()
                    )
                }
            })
            return FilterResults().also { it.values = results }

        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filteredItems = (results?.values as? List<*>)
                ?.mapNotNull { it as? Movie }
                ?: emptyList()
            notifyDataSetChanged()
        }

    }
}
