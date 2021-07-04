package com.shaun.composenavigation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shaun.composenavigation.Routes

@Composable
fun Home(navController: NavHostController) {
    var counter by remember {
        mutableStateOf(0)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {

        Column {
            Text(text = "Home, Counter is $counter", color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { counter++ }) {
                Text(text = "Increment Counter",color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                navController.navigate(Routes.Profile.route)
            }) {
                Text(text = "Navigate to Profile",color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {

                navController.navigate(Routes.Settings.route + "/$counter")

            }) {
                Text(text = "Navigate to Settings",color= Color.White)
            }

        }

    }
}