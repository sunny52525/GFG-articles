package com.gfg.bottomnavigation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.gfg.bottomnavigation.models.BottomNavItem

object Constants {

    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Search",
            icon = Icons.Filled.Search,
            route = "search"
        ),
        BottomNavItem(
            label = "Profile",
            icon = Icons.Filled.Person,
            route = "profile"
        )
    )

}