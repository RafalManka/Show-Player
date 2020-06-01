package pl.rm.app.tools

import java.util.*

fun String.containsIgnoreCase(constraint: String): Boolean {
    return toUpperCase(Locale.US).contains(constraint.toUpperCase(Locale.US))
}