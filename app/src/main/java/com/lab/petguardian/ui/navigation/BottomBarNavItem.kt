package com.lab.petguardian.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable


sealed class BottomBarNavItem(
    var title: String,
    var imageVector: ImageVector,
    var route: String
) {
    object Home : BottomBarNavItem("Home", Icons.Default.Home, "Home")
    object Plans : BottomBarNavItem("Plans", Icons.Default.Pets, "Plans")
    object Settings : BottomBarNavItem("Settings", Icons.Default.Settings, "Settings")

}