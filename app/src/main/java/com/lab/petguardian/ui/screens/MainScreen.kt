package com.lab.petguardian.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.ui.navigation.BottomBarNavGraph

@Composable
fun MainScreen(rootNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { PetBottomBar(navController = navController) }) { it ->
        Column(Modifier.padding(it)) {
            BottomBarNavGraph(
                navController = navController,
                rootNavController = rootNavController
            )
        }
    }
}