package com.example.jetpackcomposeinterop

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.jetpackcomposeinterop.ui.theme.JetpackComposeInteropTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeInteropTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InteroperabilityExample()
                }
            }
        }
    }
}

//
@Composable
fun InteroperabilityExample() {
    // A count state variable
    var counter by remember {
        mutableStateOf(0)
    }

    //Column
    Column(
        Modifier.fillMaxSize(),
        //center the children
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Text Composable showing current counter
        Text("Counter in Compose= $counter")
        Text(text = "This text is a composable")

        // Button to change the counter
        Button(onClick = { counter++ }) {
            Text(text = "Compose button to increase counter")
        }

        // Android view is a composable which can be used to display any Android view
        // it provides a factory method to create AndroidView which gives us access to context
        AndroidView(
            factory = { context ->

                //Linear layout in view system
                LinearLayout(context).apply {
                    //setting orientation to vertical
                    orientation = LinearLayout.VERTICAL

                    /**
                     * adding text view to layout using [addView] provided by [ViewGroup]
                     */
                    addView(

                        //TextView from View system
                        //creating text view and using apply scope function to add properties to text view
                        TextView(context).apply {
                            //setting text
                            text = "This is a Traditional text view "
                            //aligning text to center
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                            //setting text color
                            setTextColor(resources.getColor(R.color.black))

                        })

                    //adding text to display Counter
                    addView(
                        //Another textView from View System
                        TextView(context).apply {
                            //setting text
                            text = "Counter in view = $counter"
                            //assign Id to text view
                            id = R.id.counter1
                            //aligning text to center
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                            //setting text color
                            setTextColor(resources.getColor(R.color.black))

                        })

                    //Add another view to layout : Button
                    addView(

                        android.widget.Button(context).apply {
                            //setting text
                            text = "Traditional button to increase counter"
                            //setting text color
                            setTextColor(resources.getColor(R.color.white))
                            //set background color
                            setBackgroundColor(resources.getColor(R.color.purple_500))
                            //some padding to button
                            setPadding(20, 20, 20, 20)

                            //on click listener
                            setOnClickListener {
                                counter++
                            }

                        })
                }


            },
            // The callback to be invoked after the layout is inflated.
            update = {
                //update counter when count changes
                it.findViewById<TextView>(R.id.counter1).text = "Counter is $counter"
            },
            // we can use modifier in AndroidView
            //setting green border
            modifier = Modifier.border(3.dp, Color.Green)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeInteropTheme {
        InteroperabilityExample()
    }
}

