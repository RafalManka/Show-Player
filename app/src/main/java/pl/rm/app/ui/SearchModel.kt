package pl.rm.app.ui

sealed class SearchItem

data class Movie(
    val name: String
) : SearchItem()