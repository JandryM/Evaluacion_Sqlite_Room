package com.example.smartphone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartphone.ui.smartphone.SmartphoneEntryScreen
import com.example.smartphone.ui.smartphone.SmartphoneListScreen

@Composable
fun SmartphoneNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = "smartphone_entry",  // Inicia en la pantalla de agregar smartphone
        modifier = modifier
    ) {
        composable(route = "smartphone_entry") {
            SmartphoneEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
                navigateToList = { navController.navigate("smartphone_list") }  // Navegar a la lista de smartphones
            )
        }
        composable(route = "smartphone_list") {
            SmartphoneListScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}
