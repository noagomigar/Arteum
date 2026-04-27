package com.example.apilistapp.domain

data class Artwork(
    val id: Int,
    val title: String,
    val artistTitle: String,
    val imageUrl: String?,
    val dateDisplay: String,
    val placeOfOrigin: String,
    val mediumDisplay: String,
    val description: String?
)