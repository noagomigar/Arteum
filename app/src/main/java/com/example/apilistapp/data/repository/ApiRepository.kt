package com.example.apilistapp.data.repository

import com.example.apilistapp.data.remote.ApiInterface
import com.example.apilistapp.data.mapper.toDomain
import com.example.apilistapp.domain.Artwork

//de dades de la api al nostre donini de dades

class ApiRepository {
    val apiInterface = ApiInterface.create()

    suspend fun getArtworks() : List<Artwork>? {
        var artworks: List<Artwork>? = null
        val response = apiInterface.getArtworks()
        if (response.isSuccessful){
            artworks = response.body()!!.data.map { it.toDomain() }
        }
        return artworks
    }

    suspend fun getArtworkById(id : Int): Artwork? {
        var artwork: Artwork? = null
        val response = apiInterface.getArtworkById(id)
        if (response.isSuccessful){
            artwork = response.body()!!.data.toDomain()
        }
        return artwork
    }

}
