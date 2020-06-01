package pl.rm.app.tools.extensions

import android.view.View

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(newValue) {
        visibility = if (newValue) View.VISIBLE else View.GONE
    }