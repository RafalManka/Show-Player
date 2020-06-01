package pl.rm.player.tools

import android.content.Context
import kotlin.math.roundToInt

fun Context.dpToPixel(dp: Int): Int {
    val density = resources.displayMetrics.density
    return (dp.toFloat() * density).roundToInt()
}