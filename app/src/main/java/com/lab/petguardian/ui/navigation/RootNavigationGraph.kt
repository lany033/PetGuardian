package com.lab.petguardian.ui.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lab.petguardian.ui.screens.authScreens.LoginViewModel
import com.lab.petguardian.ui.screens.addNewPetScreen.AddNewPetScreen
import com.lab.petguardian.ui.screens.authScreens.ForgotPasswordScreen
import com.lab.petguardian.ui.screens.authScreens.WelcomeScreen
import com.lab.petguardian.ui.screens.MainScreen
import com.lab.petguardian.ui.screens.addNewPetScreen.PetViewModel
import com.lab.petguardian.ui.screens.authScreens.SignUpScreen

@Composable
fun RootNavigationGraph(context: Context, navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        composable(Graph.MAIN) {
            MainScreen(
                rootNavController = navController
            )
        }
        /*composable(route = Graph.ADDPET) {
            AddNewPetScreen(onClickBackHome = {
                navController.navigate(Graph.ROOT) {
                    popUpTo(Graph.MAIN){
                        inclusive = true
                    }
                }
            })
        }*/
    }
}

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(route = Graph.AUTHENTICATION, startDestination = AuthenticationGraph.LOGIN) {
        composable(AuthenticationGraph.LOGIN) {
            WelcomeScreen(
                onClickSignUp = { navController.navigate(AuthenticationGraph.SIGN_UP) },
                onClickForgotPassword = { navController.navigate(AuthenticationGraph.FORGOT_PASSWORD) },
                onClickHome = {
                    navController.navigate(Graph.MAIN) {
                        popUpTo(Graph.AUTHENTICATION)
                    }
                },
                onGoogleSignIn = {
                    navController.navigate(Graph.MAIN) {
                        popUpTo(Graph.AUTHENTICATION)
                    }
                }
            )
        }
        composable(AuthenticationGraph.SIGN_UP) {
            SignUpScreen(navController)
        }
        composable(AuthenticationGraph.FORGOT_PASSWORD) {
            ForgotPasswordScreen(
                backLogin = { navController.navigate(Graph.AUTHENTICATION) })
        }
    }
}


object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "main_graph"
    const val ADDPET = "addpet_graph"
}