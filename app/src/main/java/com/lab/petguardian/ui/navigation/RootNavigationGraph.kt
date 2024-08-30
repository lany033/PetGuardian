package com.lab.petguardian.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lab.petguardian.data.AuthManager
import com.lab.petguardian.ui.screens.ForgotPasswordScreen
import com.lab.petguardian.ui.screens.WelcomeScreen
import com.lab.petguardian.ui.screens.MainScreen
import com.lab.petguardian.ui.screens.SignUpScreen

@Composable
fun RootNavigationGraph(context: Context, navController: NavHostController) {

    val authManager = AuthManager(context)

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController, authManager = authManager)
        composable(Graph.HOME) {
            MainScreen(
                authManager = authManager,
                rootNavController = navController
            )
        }
    }
}

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    authManager: AuthManager
) {
    navigation(route = Graph.AUTHENTICATION, startDestination = AuthenticationGraph.LOGIN) {
        composable(AuthenticationGraph.LOGIN) {
            WelcomeScreen(
                onClickSignUp = { navController.navigate(AuthenticationGraph.SIGN_UP) },
                onClickForgotPassword = { navController.navigate(AuthenticationGraph.FORGOT_PASSWORD) },
                onClickHome = {
                    navController.navigate(Graph.HOME) {
                        popUpTo(Graph.AUTHENTICATION)
                    }
                },
                onGoogleSignIn = {
                    navController.navigate(Graph.HOME) {
                        popUpTo(Graph.AUTHENTICATION)
                    }
                },
                authManager = authManager
            )
        }
        composable(AuthenticationGraph.SIGN_UP) {
            SignUpScreen(navController, authManager)
        }
        composable(AuthenticationGraph.FORGOT_PASSWORD) {
            ForgotPasswordScreen(
                authManager,
                backLogin = { navController.navigate(Graph.AUTHENTICATION) })
        }
    }
}


object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}