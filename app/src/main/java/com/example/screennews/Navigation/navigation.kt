package com.example.screennews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.amovieapp.Screens.detail.detailscreen
import com.example.screennews.Navigation.MovieScreens
import com.example.screennews.firstS.firsts
import com.example.screennews.screen.Main

@Composable
fun movieNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.FirstScreen.name
    ) {
        composable(
            route = MovieScreens.FirstScreen.name
        ) {
            firsts(navController = navController)
        }
        composable(
            route = "${MovieScreens.HomeScreen.name}/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
        ) {
            Main(navController = navController, movie = it.arguments?.getString("movie"))
        }
        composable(
            route = "${MovieScreens.DetailedScreen.name}/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
        ) {
            detailscreen(navController = navController, it.arguments?.getString("movie"))
        }
    }
}
