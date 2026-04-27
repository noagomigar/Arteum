package com.example.apilistapp.ui.screens.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apilistapp.data.repository.FavoriteRepository
import com.example.apilistapp.data.repository.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(context: Context) : ViewModel() {

    private val repository = SettingsRepository(context)
    private val favRepo = FavoriteRepository()

    private val _isDarkMode = MutableStateFlow(repository.isDarkModeEnabled())
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    private val _isGridView = MutableStateFlow(repository.isGridViewEnabled())
    val isGridView: StateFlow<Boolean> = _isGridView.asStateFlow()

    private val _showDeleteDialog = MutableStateFlow(false)
    val showDeleteDialog: StateFlow<Boolean> = _showDeleteDialog.asStateFlow()

    fun toggleDarkMode(enabled: Boolean) {
        repository.setDarkMode(enabled)
        _isDarkMode.value = enabled
    }

    fun toggleShowMode(isGrid: Boolean) {
        repository.setGridView(isGrid)
        _isGridView.value = isGrid
    }

    fun onShowDialog() {
        _showDeleteDialog.value = true
    }

    fun onDismissDialog() {
        _showDeleteDialog.value = false
    }

    fun clearAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            favRepo.deleteAllFavorites()
            _showDeleteDialog.value = false
        }
    }
}