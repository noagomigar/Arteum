package com.example.apilistapp.data.mapper

import com.example.apilistapp.data.local.entity.ArtworkEntity
import com.example.apilistapp.data.remote.dto.Data
import com.example.apilistapp.domain.Artwork
import com.example.apilistapp.utils.stripHtml

fun Data.toDomain(): Artwork {

    val constructedImageUrl = if (image_id != null) {
        "https://www.artic.edu/iiif/2/$image_id/full/843,/0/default.jpg"
    } else {
        null
    }

    return Artwork(
        id = this.id,
        title = this.title ?: "Untitled",
        artistTitle = this.artist_title ?: "Unknown artist",
        imageUrl = constructedImageUrl,
        dateDisplay = this.date_display ?: "Unknown date",
        placeOfOrigin = this.place_of_origin ?: "Unknown origin",
        mediumDisplay = this.medium_display ?: "Technique not specified",
        description = this.description?.stripHtml() ?: "No description available for this artwork."
    )
}

fun Artwork.toEntity(): ArtworkEntity {
    return ArtworkEntity(
        id = this.id,
        title = this.title ?: "Untitled",
        artistTitle = this.artistTitle ?: "Unknown artist",
        imageUrl = this.imageUrl,
        dateDisplay = this.dateDisplay ?: "Unknown date",
        placeOfOrigin = this.placeOfOrigin ?: "Unknown origin",
        mediumDisplay = this.mediumDisplay ?: "Technique not specified",
        description = this.description ?: "No description available for this artwork."
    )
}

fun ArtworkEntity.toDomain(): Artwork {
    return Artwork(
        id = this.id,
        title = this.title ?: "Untitled",
        artistTitle = this.artistTitle ?: "Unknown artist",
        imageUrl = this.imageUrl,
        dateDisplay = this.dateDisplay ?: "Unknown date",
        placeOfOrigin = this.placeOfOrigin ?: "Unknown origin",
        mediumDisplay = this.mediumDisplay ?: "Technique not specified",
        description = this.description ?: "No description available for this artwork."
    )
}