package com.example.apilistapp.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun DetailScreen(
    artworkId: Int,
    onNavigateBack: () -> Unit,
) {
    val viewModel: DetailScreenViewModel = viewModel()
    val artwork    by viewModel.artwork.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    val isLoading  by viewModel.isLoading.collectAsState()

    LaunchedEffect(artworkId) { viewModel.loadArtwork(artworkId) }

    val cs = MaterialTheme.colorScheme

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(cs.background)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = cs.primary,
                strokeWidth = 2.dp
            )
        } else {
            artwork?.let { item ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp)
                    ) {
                        AsyncImage(
                            model = item.imageUrl,
                            contentDescription = item.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Black.copy(alpha = 0.45f), Color.Transparent)
                                    )
                                )
                        )

                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(40.dp)
                                .background(Color.White.copy(alpha = 0.18f), CircleShape)
                                .align(Alignment.TopStart),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = onNavigateBack) {
                                Icon(
                                    Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .size(40.dp)
                                .background(
                                    if (isFavorite) cs.primary.copy(alpha = 0.88f)
                                    else Color.White.copy(alpha = 0.18f),
                                    CircleShape
                                )
                                .align(Alignment.TopEnd),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(onClick = { viewModel.changeIsFavourite(item) }) {
                                Icon(
                                    if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Favourite",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 28.dp)) {

                        Text(
                            text = item.title,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = cs.onBackground,
                            lineHeight = 32.sp
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = item.artistTitle,
                            fontSize = 15.sp,
                            color = cs.secondary,
                            fontWeight = FontWeight.Medium,
                            letterSpacing = 0.3.sp
                        )

                        Spacer(Modifier.height(20.dp))
                        HorizontalDivider(color = cs.outline, thickness = 1.dp)
                        Spacer(Modifier.height(20.dp))

                        DetailItem(label = "Date",   value = item.dateDisplay,    cs = cs)
                        DetailItem(label = "Origin", value = item.placeOfOrigin,  cs = cs)
                        DetailItem(label = "Medium", value = item.mediumDisplay,  cs = cs)

                        Spacer(Modifier.height(24.dp))

                        Text(
                            text = "DESCRIPTION",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = cs.primary,
                            letterSpacing = 2.sp
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = item.description ?: "No description available.",
                            fontSize = 15.sp,
                            color = cs.onSurfaceVariant,
                            lineHeight = 24.sp
                        )

                        Spacer(Modifier.height(32.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String, cs: ColorScheme) {
    Row(
        modifier = Modifier.padding(vertical = 5.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label.uppercase(),
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = cs.primary,
            letterSpacing = 1.5.sp,
            modifier = Modifier
                .padding(top = 2.dp)
                .weight(0.28f)
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = cs.onBackground,
            modifier = Modifier.weight(0.72f)
        )
    }
}