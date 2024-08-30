package com.lab.petguardian

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lab.petguardian.ui.common.CommonStatusBarColor
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
                CommonStatusBarColor(navigationBarColor = MaterialTheme.colorScheme.background)
                RootNavigationGraph(context = this, navController = navHostController)
            }
        }
    }
}
