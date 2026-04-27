package com.example.apilistapp.data.remote

import com.example.apilistapp.data.remote.dto.ArtworkDetail
import com.example.apilistapp.data.remote.dto.Data
import com.example.apilistapp.data.remote.dto.ListOfArtworks
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("artworks")
    suspend fun getArtworks(): Response<ListOfArtworks>

    @GET("artworks/{id}")
    suspend fun getArtworkById(@Path("id") id: Int): Response<ArtworkDetail>

    companion object {
        const val BASE_URL = "https://api.artic.edu/api/v1/"

        fun create(): ApiInterface {
            val client = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}