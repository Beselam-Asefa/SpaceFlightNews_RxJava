package com.example.spaceflightnews.network

import com.google.gson.annotations.SerializedName

/**
 * a data class for the network request
 * this is the replica of the Article data class
 * this can pe replaced if we decided to change the network module
 * In the beginning I was fetching everything from the server , but at the end am only using some of
 * them , so I decided not to fetch the data am not using and save the network bandwidth
 */

data class NetworkArticleEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("newsSite")
    val newsSite: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)
