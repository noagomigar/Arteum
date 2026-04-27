package com.example.apilistapp.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes : NavKey {
    @Serializable
    data object ListScreen : Routes()

    @Serializable
    data object FavouritesScreen : Routes()

    @Serializable
    data object SettingsScreen : Routes()

    @Serializable
    data class DetailScreen(val itemId: String) : Routes()
}


sealed class ListNestedRoute: NavKey {
    @Serializable data object List: ListNestedRoute()
    @Serializable
    data class Detail(val artworkId: Int) : ListNestedRoute()}

sealed class FavoriteNestedRoute: NavKey {
    @Serializable data object FavoriteList: ListNestedRoute()
    @Serializable
    data class FavoriteDetail(val artworkId: Int) : ListNestedRoute()}