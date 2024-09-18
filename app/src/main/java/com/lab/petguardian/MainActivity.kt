package com.lab.petguardian

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lab.petguardian.ui.common.CommonStatusBarColor
import com.lab.petguardian.ui.navigation.RootNavigationGraph
import com.lab.petguardian.ui.screens.authScreens.LoginViewModel
import com.lab.petguardian.ui.screens.addNewPetScreen.PetViewModel
import com.lab.petguardian.ui.theme.PetGuardianTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    @RequiresApi(Build.VERSION_CODES.O)
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
