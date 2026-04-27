package com.example.apilistapp.data.remote.dto

data class SuggestAutocompleteAllX(
    val contexts: ContextsX,
    val input: List<String>,
    val weight: Int
)