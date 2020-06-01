package pl.rm.player

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

@ColorInt
fun Fragment.getSupportColor(@ColorRes colorRes: Int): Int? {
    return context?.let { ContextCompat.getColor(it, colorRes) }
}