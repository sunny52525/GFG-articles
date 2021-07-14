package com.gfg.scaffoldjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color.White) {
                //Scaffold we created
                ScaffoldExample()
            }

        }
    }
}

@Composable
fun ScaffoldExample() {

    //create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    //Create a coroutine scope. Opening of Drawer and snackbar should happen in background thread without blocking main thread
    val coroutineScope = rememberCoroutineScope()

    //Scaffold Composable
    Scaffold(
        //pass the scaffold state
        scaffoldState = scaffoldState,
        //pass the topbar we created
        topBar = {
            TopBar(
                //When menu is clicked open the drawer in coroutine scope
                onMenuClicked = {
                    coroutineScope.launch {
                        //to close use -> scaffoldState.drawerState.close()
                        scaffoldState.drawerState.open()
                    }
                })
        },
        //pass the bottomBar we created
        bottomBar = { BottomBar() },
        //Pass the body in content parameter
        content = {
            Body()
        },
        //pass the drawer
        drawerContent = {
            Drawer()
        },


        floatingActionButton = {
            //Create a floating action button in floatingActionButton parameter of scaffold
            FloatingActionButton(

                onClick = {
                    //When clicked open Snackbar
                    coroutineScope.launch {
                        when (scaffoldState.snackbarHostState.showSnackbar(
                            message = "Snack Bar", //Message In the snackbar
                            actionLabel = "Dismiss"
                        )) {
                            SnackbarResult.Dismissed -> {
                                //do something when snack bar is dismissed
                            }

                            SnackbarResult.ActionPerformed -> {
                                //when it appears
                            }

                        }
                    }
                }) {

                //Simple Text inside FAB
                Text(text = "X")
            }
        }
    )
}


//A function which will receive a callback to trigger to opening the drawer
@Composable
fun TopBar(onMenuClicked: () -> Unit) {

    //TopAppBar Composable
    TopAppBar(
        //Provide Title
        title = {
            Text(text = "Scaffold||GFG", color = Color.White)
        },
        //Provide the navigation Icon ( Icon on the left to toggle drawer)
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",

                modifier = Modifier.clickable(onClick = onMenuClicked), //When clicked trigger onClick Callback to trigger drawer open
                tint = Color.White
            )
        },
        //background color of topAppBar
        backgroundColor = Color(0xFF0F9D58)
    )
}

@Composable
fun BottomBar() {
    //BottomAppBar Composable
    BottomAppBar(
        backgroundColor = Color(0xFF0F9D58)
    ) {
        Text(text = "Bottom App Bar", color = Color.White)
    }
}

@Composable
fun Body() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "Body Content", color = Color(0xFF0F9D58))
    }
}


@Composable
fun Drawer() {

    //Column Composable
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        //Repeat is a loop which takes count as argument
        repeat(5) { item ->
            Text(text = "Item number $item", modifier = Modifier.padding(8.dp), color = Color.Black)
        }
    }
}

