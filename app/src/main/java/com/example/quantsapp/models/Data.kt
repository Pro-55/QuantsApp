package com.example.quantsapp.models

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("feed") val feed: List<FeedItem> = listOf()
)