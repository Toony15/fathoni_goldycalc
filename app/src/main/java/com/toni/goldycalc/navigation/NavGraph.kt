package com.toni.goldycalc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.toni.goldycalc.ui.screen.AboutGoldyScreen
import com.toni.goldycalc.ui.screen.MainScreen

@Composable
fun SetupNavbarGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutGoldyScreen(navController)
        }
    }

}