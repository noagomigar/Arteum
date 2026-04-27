package com.example.apilistapp.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {

    val isDarkMode by viewModel.isDarkMode.collectAsState()
    val isGrid by viewModel.isGridView.collectAsState()
    val showDialog by viewModel.showDeleteDialog.collectAsState()

    val cs = MaterialTheme.colorScheme

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
                text = "APP",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = cs.primary,
                letterSpacing = 2.5.sp
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Settings",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = cs.onBackground
            )
        }

        HorizontalDivider(color = cs.outline, thickness = 1.dp)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(28.dp))

            SectionLabel("Appearance", cs)
            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Dark Mode",
                    fontSize = 15.sp,
                    color = cs.onBackground,
                    fontWeight = FontWeight.Medium
                )
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { viewModel.toggleDarkMode(it) },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = cs.onPrimary,
                        checkedTrackColor = cs.primary,
                        uncheckedThumbColor = cs.onPrimary,
                        uncheckedTrackColor = cs.outline
                    )
                )
            }

            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = cs.outline.copy(alpha = 0.5f), thickness = 1.dp)
            Spacer(Modifier.height(28.dp))

            SectionLabel("Display mode", cs)
            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(cs.surface, RoundedCornerShape(12.dp))
                    .padding(4.dp)
            ) {
                ModeButton(
                    label = "List",
                    selected = !isGrid,
                    cs = cs,
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.toggleShowMode(false) }
                )
                ModeButton(
                    label = "Grid",
                    selected = isGrid,
                    cs = cs,
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.toggleShowMode(true) }
                )
            }

            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = cs.outline.copy(alpha = 0.5f), thickness = 1.dp)
            Spacer(Modifier.height(28.dp))

            SectionLabel("Data", cs)
            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = { viewModel.onShowDialog() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = cs.error),
                border = androidx.compose.foundation.BorderStroke(1.dp, cs.error.copy(alpha = 0.4f))
            ) {
                Text(
                    text = "Delete all favorites",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = cs.error,
                    letterSpacing = 0.3.sp
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.onDismissDialog() },
            containerColor = cs.background,
            shape = RoundedCornerShape(16.dp),
            title = {
                Text(
                    "Confirm deletion",
                    fontWeight = FontWeight.Bold,
                    color = cs.onBackground,
                    fontSize = 17.sp
                )
            },
            text = {
                Text(
                    "Are you sure you want to remove all your saved artworks from the local database?",
                    color = cs.onSurfaceVariant,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )
            },
            confirmButton = {
                TextButton(onClick = { viewModel.clearAllFavorites() }) {
                    Text("Delete", color = cs.error, fontWeight = FontWeight.SemiBold)
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.onDismissDialog() }) {
                    Text("Cancel", color = cs.primary, fontWeight = FontWeight.Medium)
                }
            }
        )
    }
}

@Composable
private fun SectionLabel(text: String, cs: ColorScheme) {
    Text(
        text = text.uppercase(),
        fontSize = 9.sp,
        fontWeight = FontWeight.Bold,
        color = cs.primary,
        letterSpacing = 2.sp
    )
}

@Composable
private fun ModeButton(
    label: String,
    selected: Boolean,
    cs: ColorScheme,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        shape = RoundedCornerShape(9.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) cs.primary else Color.Transparent,
            contentColor   = if (selected) cs.onPrimary else cs.onSurfaceVariant
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (selected) 2.dp else 0.dp
        )
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
}