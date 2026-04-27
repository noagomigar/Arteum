package com.example.apilistapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class ArtworkEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val artistTitle: String,
    val imageUrl: String?,
    val dateDisplay: String,
    val placeOfOrigin: String,
    val mediumDisplay: String,
    val description: String?
)