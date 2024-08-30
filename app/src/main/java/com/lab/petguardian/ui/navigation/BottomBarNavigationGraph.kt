package com.lab.petguardian.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.ui.screens.HomeScreen
import com.lab.petguardian.ui.screens.PlansScreen
import com.lab.petguardian.ui.screens.SettingsScreen

@Composable
fun BottomBarNavGraph(
    navController: NavHostController,
    authManager: AuthManager,
    rootNavController: NavHostController
) {
    NavHost(navController = navController, startDestination = BottomBarNavItem.Home.route) {
        composable(route = BottomBarNavItem.Home.route) {
            HomeScreen(
                navController = navController,
                authManager = authManager,
                onClickLogout = {
                    authManager.signOut()
                    rootNavController.navigate(Graph.AUTHENTICATION) {
                        popUpTo(Graph.ROOT) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = BottomBarNavItem.Plans.route) { PlansScreen() }
        composable(route = BottomBarNavItem.Settings.route) { SettingsScreen() }
    }
}


