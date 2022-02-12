package com.gfg.lazyanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gfg.lazyanimations.ui.theme.LazyAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ListAnimation()

                }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListAnimation() {
    // List of String to show in LazyColumn
    var names by remember {
        mutableStateOf(
            listOf(
                "GeeksForGeeks",
                "HackerRank",
                "HackerEarth",
                "CodeChef",
                "CodeForces",
                "LeetCode",
            )
        )

    }

    Column {
        //Buttons controls to add,remove,shuffle elements
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //Button to Remove random element
            Button(
                onClick = {
                    //random number between 0<=x<names.size
                    val random = (names.indices).random()



                    //remove from random index
                    names = names.filterIndexed { index, _ ->
                        index != random
                    }
                }) {
                Text(text = "Remove")
            }


            // Button to add a random string
            Button(onClick = {
                //generate a random string
                val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
                val randomString =(1..10)
                    .map { allowedChars.random() }
                    .joinToString("")

                //add random string to the list
                names = names+randomString
            }) {

                Text(text = "Add")
            }


            //Button to shuffle the list
            Button(onClick = {
                //Shuffle
                names = names.shuffled()
            }) {
                Text(text = "Shuffle")

            }
        }

        //LazyColumn
        LazyColumn {
            //show elements using items function
            items(
                //list of items to display
                items = names,
                /**
                 * Important: Pass key parameter to items/item function.
                 * Without it items won't animate, Keys should be unique,
                 * Here its set to the item in list for ease
                 */
                key = {
                it
            }) {name->

                // Text Composable to display item
                Text(
                    text = name,

                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .animateItemPlacement() //Important: add this modifier. Compose will animate items placement according to the key
                )
            }
        }
    }

}
