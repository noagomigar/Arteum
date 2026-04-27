package com.example.apilistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.apilistapp.ui.screens.detail.DetailScreen
import com.example.apilistapp.ui.screens.list.ListScreen
import com.example.apilistapp.ui.screens.settings.SettingsViewModel

@Composable
fun ListScreenNavigation(settings: SettingsViewModel) {
    val listBackStack = rememberNavBackStack(ListNestedRoute.List)

    NavDisplay(
        backStack = listBackStack,
        onBack = { listBackStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<ListNestedRoute.List> {
                ListScreen(settings) { artworkId ->
                    listBackStack.add(
                        ListNestedRoute.Detail(
                            artworkId
                        )
                    )
                }
            }

            entry<ListNestedRoute.Detail> { nestedRoute ->
                DetailScreen(
                    artworkId = nestedRoute.artworkId
                ) { listBackStack.removeLastOrNull() }
            }
        })
}