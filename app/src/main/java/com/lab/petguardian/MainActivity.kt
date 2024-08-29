package com.lab.petguardian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lab.petguardian.ui.navigation.RootNavigationGraph
import com.lab.petguardian.ui.theme.PetGuardianTheme

class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { false }
        enableEdgeToEdge()

        setContent {
            navHostController = rememberNavController()
            PetGuardianTheme {
                RootNavigationGraph(context = this, navController = navHostController)
            }
        }
    }
}
