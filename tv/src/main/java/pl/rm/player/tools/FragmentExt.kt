package pl.rm.player.tools

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

@ColorInt
fun Fragment.getSupportColor(@ColorRes colorRes: Int): Int? {
    return context?.let { ContextCompat.getColor(it, colorRes) }
}

fun Fragment.getSupportDrawable(@DrawableRes drawable: Int): Drawable? {
    return context?.let { ContextCompat.getDrawable(it, drawable) }
}