package com.shaun.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shaun.composenavigation.screens.Home
import com.shaun.composenavigation.screens.Profile
import com.shaun.composenavigation.screens.Setting
import com.shaun.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ScreenMain()
                }
            }
        }
    }
}

@Composable
fun ScreenMain() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {

        composable(Routes.Home.route) {
            Home(navController = navController)
        }

        composable(Routes.Profile.route) {
            Profile()
        }
        composable(Routes.Settings.route + "/{id}") { navBackStack ->
            val counter = navBackStack.arguments?.getString("id")

            Setting(counter = counter)

        }
    }
}
