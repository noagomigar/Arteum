package com.example.apilistapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apilistapp.data.local.entity.ArtworkEntity
import com.example.apilistapp.domain.Artwork
@Dao
interface ArtworkDao {
    @Query("SELECT * FROM favorites")
    suspend fun getAllArtworks(): MutableList<ArtworkEntity>
    @Query("SELECT * FROM favorites WHERE id = :artworkId")
    suspend fun getArtworkById(artworkId: Int): ArtworkEntity?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArtwork(artwork: ArtworkEntity)
    @Delete
    suspend fun deleteArtwork(artwork: ArtworkEntity)
    @Query("DELETE FROM favorites")
    suspend fun clearAllFavorites()
}