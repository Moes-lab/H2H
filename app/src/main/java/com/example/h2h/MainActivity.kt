package com.example.h2h

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.ui.res.painterResource


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

//UI

@Composable
fun HomeScreen(
    goDetails: () -> Unit,
    goMap: () -> Unit,
    goCommunity: () -> Unit,
    goProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // ===== TOP CONTENT (placeholder for your images/UI) =====
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.heart2heart),
                contentDescription = "H2H logo",
                modifier = Modifier
                    .size(350.dp)
                    .offset(x = 25.dp, y= -80.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "H2H logo",
                modifier = Modifier
                    .size(200.dp)
                    .offset(x = 40.dp, y= -180.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Spacer(modifier = Modifier.height(20.dp))

            // Example action buttons
            Button(onClick = goDetails) {
                Text("Disconnect")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = {
                val msg = "Data from Home"
                // example send data to profile
                goProfile()
            }) {
                Text("Activate Level 1")
            }
            Button(onClick = {
                val msg = "Data from Home"
                // example send data to profile
                goProfile()
            }) {
                Text("Activate Level 2")
            }
        }

        // ===== BOTTOM NAVIGATION BAR =====
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.LightGray)
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 16.dp)
        ) {
            NavItem("Home", R.drawable.home) { /* Allerede på home */ }
            NavItem("Map", R.drawable.map) { goMap() }
            NavItem("Community", R.drawable.community) { goCommunity() }
            NavItem("Profile", R.drawable.profile) { goProfile() }
        }
    }
}

@Composable
fun NavItem(label: String, iconResId: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            modifier = Modifier.size(32.dp) // Du kan justere størrelsen her
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            // Valgfrit: Gør teksten pink hvis det er Home (ligesom dit ikon)
            color = if (label == "Home") Color(0xFFE91E63) else Color.Gray
        )
    }
}
// ================= OTHER SCREENS =================

@Composable
fun DetailsScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("Details Screen", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Back") }
    }
}

@Composable
fun MapScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("Map Screen", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Back") }
    }
}

@Composable
fun CommunityScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("Community Screen", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Back") }
    }
}

@Composable
fun ProfileScreen(name: String, onBack: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("Profile Screen", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("User: $name")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Back") }
    }
}
