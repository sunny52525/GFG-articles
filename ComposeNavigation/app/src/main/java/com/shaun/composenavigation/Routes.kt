package com.shaun.composenavigation

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Profile : Routes("profile")
    object Settings : Routes("setting")
}
