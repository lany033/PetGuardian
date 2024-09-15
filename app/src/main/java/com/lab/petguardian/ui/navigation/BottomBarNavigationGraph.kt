package com.lab.petguardian.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.ui.screens.HomeScreen
import com.lab.petguardian.ui.screens.PlansScreen
import com.lab.petguardian.ui.screens.ProfileScreen
import com.lab.petguardian.ui.screens.SettingsScreen

@Composable
fun BottomBarNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController
) {
    NavHost(navController = navController, startDestination = BottomBarNavItem.Home.route) {
        homeNavGraph(navController = navController, rootNavController = rootNavController)
        composable(route = BottomBarNavItem.Plans.route) { PlansScreen() }
        composable(route = BottomBarNavItem.Settings.route) { SettingsScreen() }
    }
}

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    rootNavController: NavHostController
) {
    navigation(route = BottomBarNavItem.Home.route, startDestination = HomeGraph.HOME) {
        composable(route = HomeGraph.HOME) {
            HomeScreen(
                onClickLogout = {
                    rootNavController.navigate(Graph.AUTHENTICATION) {
                        popUpTo(Graph.ROOT) {
                            inclusive = true
                        }
                    }
                },
                onClickProfile = {
                    navController.navigate(HomeGraph.PROFILE)
                },
                onClickAddPet = {
                    rootNavController.navigate(Graph.ADDPET)
                }
            )
        }
        composable(route = HomeGraph.PROFILE) { ProfileScreen() }

    }
}

object HomeGraph {
    const val HOME = "home_graph"
    const val PROFILE = "profile_graph"
}


