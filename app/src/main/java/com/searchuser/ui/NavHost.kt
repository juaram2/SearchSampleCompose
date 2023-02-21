package com.searchuser.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.searchuser.ui.page.RepoScreen
import com.searchuser.ui.page.SearchResultScreen
import com.searchuser.ui.page.SearchScreen

@Composable
fun NavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            SearchScreen(navController)
        }
        composable("search/{query}",
            arguments = listOf(navArgument("query") { type = NavType.StringType })
        ) {
            SearchResultScreen(navController)
        }
        composable("repo/{username}",
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) {
            RepoScreen()
        }
    }
}