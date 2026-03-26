package com.example.h2h

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.h2h.components.CommunityScreen
import com.example.h2h.components.DetailsScreen
import com.example.h2h.ui.screens.HomeScreen
import com.example.h2h.components.MapScreen
import com.example.h2h.components.ProfileScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "home") {

                composable("home") {
                    HomeScreen(
                        goDetails = { navController.navigate("details") },
                        goMap = { navController.navigate("map") },
                        goCommunity = { navController.navigate("community") },
                        goProfile = {
                            val name = "H2H User"
                            navController.navigate("profile/$name")
                        }
                    )
                }

                composable("details") {
                    DetailsScreen(onBack = { navController.popBackStack() })
                }

                composable("map") {
                    MapScreen(onBack = { navController.popBackStack() })
                }

                composable("community") {
                    CommunityScreen(onBack = { navController.popBackStack() })
                }

                composable(
                    route = "profile/{name}",
                    arguments = listOf(navArgument("name") { type = NavType.StringType })
                ) { backStackEntry ->

                    val name = backStackEntry.arguments?.getString("name") ?: return@composable

                    ProfileScreen(
                        name = name,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
