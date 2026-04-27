package com.example.apilistapp.data.local



import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apilistapp.data.local.dao.ArtworkDao
import com.example.apilistapp.data.local.entity.ArtworkEntity

@Database(entities = arrayOf(ArtworkEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun artworkDao(): ArtworkDao
}

