package com.example.spaceflightnews.model

/**
 * a domain model
 */

data class Article(
    val id: String,
    val imageUrl: String,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val url: String
)
