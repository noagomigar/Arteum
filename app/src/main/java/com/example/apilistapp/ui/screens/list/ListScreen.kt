package com.example.apilistapp.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.apilistapp.domain.Artwork
import com.example.apilistapp.ui.screens.settings.SettingsViewModel

@Composable
fun ListScreen(settingsViewModel: SettingsViewModel, onNavigateToDetail: (Int) -> Unit) {

    val viewModel: ListScreenViewModel = viewModel()
    val artworks by viewModel.artworks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isGrid by settingsViewModel.isGridView.collectAsState()

    val cs = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme. background)
    ) {

        Column(
            modifier = Modifier
                .background(cs.background)
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Text(
                text = "COLLECTION",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = cs.primary,
                letterSpacing = 2.5.sp
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Artworks",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = cs.onBackground
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchQueryChange(it) }, // ← VM handles it
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Search by title…", color = cs.outline, fontSize = 14.sp)
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = cs.primary,
                        modifier = Modifier.size(18.dp)
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = cs.primary,
                    unfocusedBorderColor = cs.outline,
                    focusedTextColor = cs.onBackground,
                    unfocusedTextColor = cs.onBackground,
                    cursorColor = cs.primary,
                    focusedContainerColor = cs.background,
                    unfocusedContainerColor = cs.surface,
                ),
                textStyle = LocalTextStyle.current.copy(fontSize = 14.sp)
            )
        }

        HorizontalDivider(color = cs.outline, thickness = 1.dp)

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    color = cs.primary,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(32.dp)
                )
            }
        } else {

            if (isGrid) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(vertical = 16.dp)
                ) {
                    items(artworks) { artwork ->
                        ArtworkGridItem(artwork) { onNavigateToDetail(artwork.id) }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(artworks) { artwork ->
                        ArtworkListItem(artwork) { onNavigateToDetail(artwork.id) }
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

@Composable
fun ArtworkListItem(artwork: Artwork, onClick: () -> Unit) {
    val cs = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(cs.surfaceVariant)
        ) {
            AsyncImage(
                model = artwork.imageUrl,
                contentDescription = artwork.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = artwork.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = cs.onBackground,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 20.sp
            )
        }
        Text("›", fontSize = 22.sp, color = cs.outline, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun ArtworkGridItem(artwork: Artwork, onClick: () -> Unit) {
    val cs = MaterialTheme.colorScheme
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f)
            .clip(RoundedCornerShape(12.dp))
            .background(cs.surfaceVariant)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = artwork.imageUrl,
            contentDescription = artwork.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.72f))
                    )
                )
        )
        Text(
            text = artwork.title,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = 16.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
        )
    }
}