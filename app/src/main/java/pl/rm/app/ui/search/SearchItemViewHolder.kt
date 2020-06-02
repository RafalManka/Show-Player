package pl.rm.app.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pl.rm.app.R
import java.util.*


class SearchItemViewHolder(parent: ViewGroup, private var onclickListener: (Movie) -> Unit) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.row_search_item, parent, false)
    ) {
    init {
        itemView.setOnClickListener { view ->
            val tag = view.tag as? Movie
            if (tag != null) {
                onclickListener(tag)
            }
        }
    }

    private val title: TextView by lazy {
        itemView.findViewById<TextView>(
            R.id.title
        )
    }

    private val image: ImageView by lazy {
        itemView.findViewById<ImageView>(
            R.id.image
        )
    }

    fun bind(model: Movie) {
        title.text = model.title.toUpperCase(Locale.US)
        Picasso.get()
            .load(model.image)
            .into(image)
        itemView.tag = model
    }

}