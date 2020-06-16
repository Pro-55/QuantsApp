package com.example.quantsapp.models

import com.google.gson.annotations.SerializedName

data class FeedItem(
    @SerializedName("id") val _id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String?,
    @SerializedName("status") val status: String,
    @SerializedName("profilePic") val profilePic: String,
    @SerializedName("timeStamp") val timeStamp: String,
    @SerializedName("url") val url: String?
)