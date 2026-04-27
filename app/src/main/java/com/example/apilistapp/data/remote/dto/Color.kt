package com.example.apilistapp.data.remote.dto

import androidx.compose.ui.graphics.Color

data class Color(
    val h: Int,
    val l: Int,
    val percentage: Double,
    val population: Int,
    val s: Int
) {
    companion object {
    }
}