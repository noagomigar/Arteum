package com.example.apilistapp.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.repository.FavoriteRepository
import com.example.apilistapp.domain.Artwork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    private val favRepo = FavoriteRepository()

    private val _favorites = MutableStateFlow<List<Artwork>>(emptyList())
    val favorites: StateFlow<List<Artwork>> = _favorites.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _isLoading.value = true
            val list = favRepo.getFavorites()
            _favorites.value = list
            _isLoading.value = false
        }
    }
}