package pl.rm.core.state

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("name") val name: String,
    @SerializedName("videos") val videos: List<Media>
)

data class Media(
    @SerializedName("title") val title: String,
    @SerializedName("subtitle") val subtitle: String,
    @SerializedName("description") val description: String,
    @SerializedName("sources") val sources: List<String>,
    @SerializedName("thumb") val thumb: String
)