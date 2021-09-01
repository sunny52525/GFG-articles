package com.gfg.bottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gfg.bottomnavigation.ui.components.HomeScreen
import com.gfg.bottomnavigation.ui.components.ProfileScreen
import com.gfg.bottomnavigation.ui.components.SearchScreen
import com.gfg.bottomnavigation.ui.theme.BottomNavigationTheme
import com.gfg.bottomnavigation.utils.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationTheme {
                val navController = rememberNavController()

                Surface(color = Color.White) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }, content = {
                            NavHostContainer(navController = navController)
                        }
                    )
                }
            }
        }
    }


}


@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation(backgroundColor = Color(0xFF0F9D58)) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }


    }
}

@Composable
fun NavHostContainer(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = "home", builder = {

        composable("home") {
            HomeScreen()
        }
        composable("search") {
            SearchScreen()
        }

        composable("profile") {
            ProfileScreen()
        }
    })

}