package com.lab.petguardian.ui.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lab.petguardian.ui.navigation.BottomBarNavItem

@Composable
fun PetBottomBar(navController: NavHostController){
    val items = listOf(
        BottomBarNavItem.Home,
        BottomBarNavItem.Plans,
        BottomBarNavItem.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(text = item.title, fontWeight = FontWeight.Bold) },
                icon = {
                    Icon(
                        imageVector = item.imageVector,
                        contentDescription = item.title,
                        modifier = Modifier.size(26.dp)
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = { navController.navigate(item.route) }
            )
        }
    }
}