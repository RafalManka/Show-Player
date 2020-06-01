package pl.rm.core.state

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name") val name: String,
    @SerializedName("videos") val videos: List<Item>
)

sealed class Item

data class Movie(
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("description") val description: String,
    @SerializedName("sources") val sources: List<String>,
    @SerializedName("thumb") val thumb: String
) : Item()