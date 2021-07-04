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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                ScreenMain()
            }
        }
    }
}


@Composable
fun ScreenMain() {

    val navController = rememberNavController()

    /**
     * NavHost Builds a navGraph to handle navigation, set the start destination to Home and
     * provide the navController which will control the navigation.
     */
    NavHost(navController = navController, startDestination = Routes.Home.route) {

        //First route : Home
        composable(Routes.Home.route) {

            //Lay down the Home Composable and pass the navController
            Home(navController = navController)
        }

        //Another Route : Profile
        composable(Routes.Profile.route) {
            //Profile Screen
            Profile()
        }

        //Settings Route, Notice the "/{id}" in last, its the argument passed down from homeScreen
        composable(Routes.Settings.route + "/{id}") { navBackStack ->

            //Extracting the argument
            val counter = navBackStack.arguments?.getString("id")

            //Setting screen, Pass the extracted Counter
            Setting(counter = counter)

        }
    }
}
