package com.example.apilistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.apilistapp.ui.navigation.NavigationWrapper
import com.example.apilistapp.ui.screens.settings.SettingsViewModelFactory
import com.example.apilistapp.ui.screens.settings.SettingsViewModel
import com.example.apilistapp.ui.theme.APIListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val settingsVM: SettingsViewModel by viewModels {
            SettingsViewModelFactory(this)
        }

        setContent {
            val isDarkMode by settingsVM.isDarkMode.collectAsStateWithLifecycle()

            APIListAppTheme(darkTheme = isDarkMode) {
                NavigationWrapper(settingsVM)
            }
        }
    }
}