package com.lab.petguardian.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lab.petguardian.ui.navigation.BottomBarNavGraph
import com.lab.petguardian.ui.navigation.HomeGraph

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(rootNavController: NavHostController) {
    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(bottomBar = {
        if (currentRoute != HomeGraph.PROFILE && currentRoute != HomeGraph.ADD_PET && currentRoute != HomeGraph.DETAIL_PET && currentRoute != HomeGraph.ADD_PLAN) {
            PetBottomBar(navController = navController)
        }
    }) { it ->
        Column(Modifier.padding(it)) {
            BottomBarNavGraph(
                navController = navController,
                rootNavController = rootNavController
            )
        }
    }
}