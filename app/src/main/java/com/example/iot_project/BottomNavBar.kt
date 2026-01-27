package com.example.iot_project

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.DoorFront
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

val bottomNavItems = listOf(

    BottomNavItem(
        route = "gates",
        label = "Gates",
        icon = Icons.Filled.DoorFront
        // alternatywa: Icons.Filled.Lock
    ),

    BottomNavItem(
        route = "clients",
        label = "Clients",
        icon = Icons.Filled.People
        // alternatywa: Icons.Filled.Person
    ),

    BottomNavItem(
        route = "access_logs",
        label = "Access Logs",
        icon = Icons.Filled.History
        // alternatywa: Icons.Filled.ReceiptLong
    ),

    BottomNavItem(
        route = "buildings",
        label = "Buildings",
        icon = Icons.Filled.Apartment
        // alternatywa: Icons.Filled.Business
    )
)
data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    val currentDestination by navController
        .currentBackStackEntryAsState()

    val currentRoute = currentDestination
        ?.destination
        ?.route

    NavigationBar {
        bottomNavItems.forEach { item ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(text = item.label)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}
