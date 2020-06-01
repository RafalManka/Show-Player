package pl.rm.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.rm.app.R
import pl.rm.app.ui.Movie
import pl.rm.app.ui.SearchItem

class SearchItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.row_search_item, parent, false)
) {

    private val title: TextView by lazy { itemView.findViewById<TextView>(R.id.title) }

    fun bind(model: SearchItem) {
        when (model) {
            is Movie -> {
                title.text = model.name
            }
        }
    }

}