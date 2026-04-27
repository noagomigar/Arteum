package com.example.apilistapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
sealed class BottomBarItem(val route: Routes, val label: String, val icon: ImageVector) {
    data object Home      : BottomBarItem(Routes.ListScreen,       "Home",      Icons.Default.Palette)
    data object Favorites : BottomBarItem(Routes.FavouritesScreen, "Favorites", Icons.Default.Favorite)
    data object Settings  : BottomBarItem(Routes.SettingsScreen,   "Settings",  Icons.Default.Settings)
}
val bottomBarItems = listOf(BottomBarItem.Home, BottomBarItem.Favorites, BottomBarItem.Settings)