package com.example.apilistapp.data.remote.dto

data class ListOfArtworks(
    val config: Config,
    val data: List<Data>,
    val info: Info,
    val pagination: Pagination
)