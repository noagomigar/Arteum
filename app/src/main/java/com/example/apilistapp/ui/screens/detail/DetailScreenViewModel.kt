package com.example.apilistapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.repository.ApiRepository
import com.example.apilistapp.data.repository.FavoriteRepository
import com.example.apilistapp.domain.Artwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailScreenViewModel : ViewModel() {
    private val apiRepo = ApiRepository()
    private val favRepo = FavoriteRepository()

    private val _artwork = MutableStateFlow<Artwork?>(null)
    val artwork: StateFlow<Artwork?> = _artwork.asStateFlow()

    private val _isFavourite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavourite.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadArtwork(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true

            val response = apiRepo.getArtworkById(id)

            val favorite = favRepo.getFavoriteById(id)

            withContext(Dispatchers.Main) {
                _artwork.value = response
                _isFavourite.value = favorite != null
                _isLoading.value = false
            }
        }
    }

    fun changeIsFavourite(artwork: Artwork) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_isFavourite.value) {
                favRepo.deleteFavorite(artwork)
            } else {
                favRepo.saveAsFavorite(artwork)
            }
            withContext(Dispatchers.Main) {
                _isFavourite.value = !_isFavourite.value
            }
        }
    }
}