package com.example.apilistapp.data.repository

import com.example.apilistapp.APIListApplication
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.data.mapper.toEntity
import com.example.apilistapp.domain.Artwork

class FavoriteRepository {
    val daoInterface = APIListApplication.database.artworkDao()
    suspend fun saveAsFavorite(artwork: Artwork) = daoInterface.addArtwork(artwork.toEntity())
    suspend fun deleteFavorite(artwork: Artwork)= daoInterface.deleteArtwork(artwork.toEntity())
    suspend fun isFavorite(artworkId: Int) = daoInterface.getArtworkById(artworkId = artworkId)?.toDomain()
    suspend fun getFavoriteById(artworkId: Int) = daoInterface.getArtworkById(artworkId)?.toDomain()
    suspend fun getFavorites() = daoInterface.getAllArtworks().map { it.toDomain() }.toMutableList()
    suspend fun deleteAllFavorites() = daoInterface.clearAllFavorites()
}
