package com.example.apilistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.screens.detail.DetailScreen
import com.example.apilistapp.ui.screens.favorites.FavouritesScreen
import com.example.apilistapp.ui.screens.settings.SettingsViewModel


@Composable
fun FavouritesScreenNavigation(settings: SettingsViewModel) {
    val listBackStack = rememberNavBackStack(FavoriteNestedRoute.FavoriteList)

    NavDisplay(
        backStack = listBackStack,
        onBack = { listBackStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<FavoriteNestedRoute.FavoriteList> {
                FavouritesScreen(
                    onNavigateToDetail = { artworkId ->
                        listBackStack.add(FavoriteNestedRoute.FavoriteDetail(artworkId))
                    }
                )
            }

            entry<FavoriteNestedRoute.FavoriteDetail> { nestedRoute ->
                DetailScreen(
                    artworkId = nestedRoute.artworkId,
                    onNavigateBack = { listBackStack.removeLastOrNull() }
                )
            }
        }
    )
}