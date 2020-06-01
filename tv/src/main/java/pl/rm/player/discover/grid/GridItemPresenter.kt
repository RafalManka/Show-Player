package pl.rm.player.discover.grid

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.Presenter
import pl.rm.player.R

private const val GRID_ITEM_WIDTH = 300
private const val GRID_ITEM_HEIGHT = 200

class GridItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = TextView(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
            GRID_ITEM_WIDTH,
            GRID_ITEM_HEIGHT
        )
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.setBackgroundColor(ContextCompat.getColor(parent.context, R.color.default_background))
        view.setTextColor(Color.WHITE)
        view.gravity = Gravity.CENTER
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        (viewHolder.view as TextView).text = item as String
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {

    }
}

