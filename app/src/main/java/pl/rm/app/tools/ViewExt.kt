package pl.rm.app.tools

import android.view.View

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(newValue) {
        visibility = if (newValue) View.VISIBLE else View.GONE
    }