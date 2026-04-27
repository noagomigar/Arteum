package com.example.apilistapp.utils

import android.text.Html

fun String.stripHtml(): String =
    Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT).toString().trim()