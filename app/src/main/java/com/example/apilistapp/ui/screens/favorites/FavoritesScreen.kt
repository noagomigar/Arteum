package com.example.apilistapp.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.apilistapp.ui.screens.list.ArtworkListItem

@Composable
fun FavouritesScreen(onNavigateToDetail: (Int) -> Unit) {

    val viewModel: FavoritesViewModel = viewModel()
    val favorites by viewModel.favorites.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val cs = MaterialTheme.colorScheme

    LaunchedEffect(Unit) { viewModel.loadFavorites() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(cs.background)
    ) {
        Column(
            modifier = Modifier
                .background(cs.background)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "MY",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = cs.primary,
                letterSpacing = 2.5.sp
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Favorites",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = cs.onBackground
            )
        }

        HorizontalDivider(color = cs.outline, thickness = 1.dp)

        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        color = cs.primary,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            favorites.isEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("♡", fontSize = 48.sp, color = cs.outline)
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = "NO SAVED ARTWORKS",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = cs.outline,
                            letterSpacing = 2.sp
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = "Browse the collection and save\nyour favourite pieces.",
                            fontSize = 13.sp,
                            color = cs.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(favorites) { artwork ->
                        ArtworkListItem(
                            artwork = artwork,
                            onClick = { onNavigateToDetail(artwork.id) }
                        )
                        HorizontalDivider(
                            color = cs.outline.copy(alpha = 0.5f),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                    }
                }
            }
        }
    }
}