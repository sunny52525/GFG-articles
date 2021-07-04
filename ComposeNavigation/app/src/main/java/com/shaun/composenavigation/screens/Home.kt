package com.shaun.composenavigation.screens

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


/**
 * Simple Composable function which receives navHostController which is used
 * to which functions related to navigation
 */
@Composable
fun Home(navController: NavHostController) {

    //Create a basic counter to display on screen
    var counter by remember {
        mutableStateOf(0)
    }

    //Box composable to center Items
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {

        //A Column composable
        Column {

            //A Text Composable to show counter on Screen
            Text(text = "Home, Counter is $counter", color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))

            //A button Composable which when clicked will increase the counter
            Button(onClick = { counter++ }) {
                Text(text = "Increment Counter", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))
            //A button composable to navigate to Profile Screen
            Button(onClick = {
                /**
                 * User navController to navigate to Profile Screen
                 * [Routes.Profile.route] is a string which describes route we created in [Routes] class
                 */
                navController.navigate(Routes.Profile.route)
            }) {
                Text(text = "Navigate to Profile", color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))
            //A Button Composable to navigate to Settings Screen when clicked
            Button(onClick = {
                /**
                 * User navController to navigate to Profile Screen
                 * [Routes.Settings.route] is a string which describes route we created in [Routes] class.
                 * Pass counter as argument by adding to the end. The final route will become
                 * (lets say counter value is 8) "../settings/8"
                 */
                navController.navigate(Routes.Settings.route + "/$counter")

            }) {
                Text(text = "Navigate to Settings", color = Color.White)
            }

        }

    }
}