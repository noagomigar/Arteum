package com.example.apilistapp.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.repository.ApiRepository
import com.example.apilistapp.domain.Artwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListScreenViewModel : ViewModel() {
    private val apiRepo = ApiRepository()

    private var allArtworks: List<Artwork> = emptyList()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _artworks = MutableStateFlow<List<Artwork>>(emptyList())
    val artworks: StateFlow<List<Artwork>> = _artworks.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadArtworks()
    }

    fun loadArtworks() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepo.getArtworks()
            withContext(Dispatchers.Main) {
                allArtworks = response ?: emptyList()
                applyFilter(_searchQuery.value)
                _isLoading.value = false
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        applyFilter(query)
    }

    private fun applyFilter(query: String) {
        _artworks.value = if (query.isBlank()) {
            allArtworks
        } else {
            allArtworks.filter { it.title.contains(query, ignoreCase = true) }
        }
    }
}