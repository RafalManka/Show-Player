package pl.rm.player.discover.cards

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.BaseCardView.CARD_TYPE_INFO_UNDER
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import pl.rm.player.R
import pl.rm.player.discover.Movie
import pl.rm.player.tools.dpToPixel


private const val CARD_WIDTH = 313
private const val CARD_HEIGHT = 176

class CardViewHolder(view: View) : Presenter.ViewHolder(view) {

    private val imageCard: ImageCardView = view as ImageCardView
    private val placeholderImage: Drawable? =
        ContextCompat.getDrawable(view.context, R.drawable.img_movie_placeholder)


    init {
        imageCard.cardType = CARD_TYPE_INFO_UNDER
        imageCard.mainImage = placeholderImage
        imageCard.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
    }

    var movie: Movie? = null
        set(value) {
            field = value
            imageCard.titleText = value?.title
            imageCard.contentText = value?.subtitle
            loadMainImage(value)
        }

    private fun loadMainImage(value: Movie?) {
        val placeholder = placeholderImage ?: return
        Picasso.get()
            .load(value?.image)
            .resize(
                view.context.dpToPixel(CARD_WIDTH),
                view.context.dpToPixel(CARD_HEIGHT)
            )
            .error(placeholder)
            .into(target)
    }

    private val target = object : Target {
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            imageCard.mainImage = errorDrawable
        }

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            val bitmapDrawable = BitmapDrawable(view.context.resources, bitmap)
            imageCard.setMainImageScaleType(ImageView.ScaleType.CENTER_CROP)
            imageCard.mainImage = bitmapDrawable
        }
    }
}


