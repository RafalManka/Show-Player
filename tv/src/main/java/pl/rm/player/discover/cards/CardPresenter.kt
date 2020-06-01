package pl.rm.player.discover.cards


import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import pl.rm.player.R
import pl.rm.player.discover.MovieModel


class CardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): CardViewHolder {
        val cardView = ImageCardView(parent.context)
        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        cardView.setBackgroundColor(parent.context.resources.getColor(R.color.default_background))
        return CardViewHolder(cardView)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        when (viewHolder) {
            is CardViewHolder -> viewHolder.movie = item as? MovieModel
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
    }

    override fun onViewAttachedToWindow(viewHolder: ViewHolder?) {
        // TO DO
    }

}
