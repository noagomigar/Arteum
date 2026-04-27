package com.example.apilistapp.data.remote.dto

data class SuggestAutocompleteAll(
    val contexts: Contexts,
    val input: List<String>,
    val weight: Int
)