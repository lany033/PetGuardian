package com.lab.petguardian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.lab.petguardian.ui.screens.HomeScreen
import com.lab.petguardian.ui.screens.LoginScreen
import com.lab.petguardian.ui.screens.PetDetailScreen
import com.lab.petguardian.ui.theme.PetGuardianTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { false }
        enableEdgeToEdge()
        setContent {
            PetGuardianTheme {
                //LoginScreen()
                //HomeScreen()
                PetDetailScreen()
            }
        }
    }
}
