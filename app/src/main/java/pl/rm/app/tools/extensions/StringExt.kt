package pl.rm.app.tools.extensions

import android.net.Uri
import java.util.*

fun String.containsIgnoreCase(constraint: String): Boolean {
    return toUpperCase(Locale.US).contains(constraint.toUpperCase(Locale.US))
}

val String.uri: Uri
    get() = Uri.parse(this)