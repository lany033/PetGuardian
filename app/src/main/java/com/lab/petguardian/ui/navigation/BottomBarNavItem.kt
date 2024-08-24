package com.lab.petguardian.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavItem(
    var title: String,
    var imageVector: ImageVector,
    var route: String
) {
    object Home : BottomBarNavItem("Home", Icons.Default.Home, "Home")
    object Workouts : BottomBarNavItem("Pet?", Icons.Default.Pets, "Pet?")
    object Profile : BottomBarNavItem("Settings", Icons.Default.Settings, "Settings")
}