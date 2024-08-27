package com.lab.petguardian.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lab.petguardian.ui.screens.ForgotPasswordScreen
import com.lab.petguardian.ui.screens.HomeScreen
import com.lab.petguardian.ui.screens.LoginScreen
import com.lab.petguardian.ui.screens.MainScreen
import com.lab.petguardian.ui.screens.SignUpScreen

@Composable
fun RootNavigationGraph(context: Context, navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        composable(Graph.HOME) {
            MainScreen()
        }
    }
}

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(route = Graph.AUTHENTICATION, startDestination = AuthenticationGraph.LOGIN) {
        composable(AuthenticationGraph.LOGIN) {
            LoginScreen(
                onClickSignUp = { navController.navigate(AuthenticationGraph.SIGN_UP) },
                onClickForgotPassword = { navController.navigate(AuthenticationGraph.FORGOT_PASSWORD) },
                onClickHome = { navController.navigate(Graph.HOME) }
            )
        }
        composable(AuthenticationGraph.SIGN_UP) {
            SignUpScreen()
        }
        composable(AuthenticationGraph.FORGOT_PASSWORD) {
            ForgotPasswordScreen()
        }
    }
}


object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}