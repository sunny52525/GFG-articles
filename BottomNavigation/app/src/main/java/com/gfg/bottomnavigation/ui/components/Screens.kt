package com.gfg.bottomnavigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Home", color = Color.Black)
    }
}

@Composable
fun SearchScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        contentAlignment = Alignment.Center
    ) {
        Text(text = "Search", color = Color.Black)
    }
}


@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),

        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile", color = Color.Black)
    }

}