package com.shaun.composenavigation


//It contains route names to all three screens
sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Profile : Routes("profile")
    object Settings : Routes("setting")
}
