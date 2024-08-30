package com.lab.petguardian.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CommonStatusBarColor(
    statusBarColor: Color? = null,
    navigationBarColor: Color? = null,
){
    val systemUiController = rememberSystemUiController()
    //val darkTheme = isSystemInDarkTheme()
    val tertiary = MaterialTheme.colorScheme.tertiary
    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor ?: Color.Transparent,
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = navigationBarColor ?: tertiary,
            darkIcons = false
        )
    }
}