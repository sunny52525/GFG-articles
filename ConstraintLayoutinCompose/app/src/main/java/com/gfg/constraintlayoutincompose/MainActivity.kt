package com.gfg.constraintlayoutincompose

import com.gfg.constraintlayoutincompose.ui.theme.Green
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                ConstraintLayoutExample()
            }

        }
    }
}


@Preview
@Composable
fun ConstraintLayoutExample() {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        val (logo, topBar, companyName) = createRefs()


        //TopAppBar Composable
        TopAppBar(
            modifier = Modifier
                /**
                 *   Use constraint as modifier to supply the reference to the composable
                 *   Similar to putting `android:id=@+id/topBar`  in view System.
                 *   Then inside lambda function,create the constraints
                 */
                .constrainAs(topBar) {

                    //top of TopAppBar constraints to top of parent
                    top.linkTo(parent.top)

                    //start of TopAppBar constraints to start of parent
                    start.linkTo(parent.start)

                    //end of TopAppBar constraints to end of parent
                    end.linkTo(parent.end)
                },

            backgroundColor = Green
        ) {

            //Contents for topAppBar
            Text(
                text = "Geeks for Geeks | Constraint Layout", color = Color.White
            )
        }

        //Image Composable
        Image(
            //Setting the image saved in drawable
            painter = painterResource(id = R.drawable.gfg),
            contentDescription = "company logo",
            modifier = Modifier
                .size(70.dp)

                //Pass the reference
                .constrainAs(logo) {

                    //constraint top to bottom of topAppBar
                    top.linkTo(topBar.bottom)

                    //constraint start to parent start
                    start.linkTo(parent.start)

                    //constraint end to parent end
                    end.linkTo(parent.end)

                    //Constraint bottom to top of bottom text as it will be in bottom most
                    bottom.linkTo(companyName.top)
                })


        //Text Composable
        Text(
            text = "Geeks for geeks",
            color = Color.Black,
            modifier = Modifier.
                //Passing the reference
                constrainAs(companyName) {

                    //constraint start to parent start
                    start.linkTo(parent.start)

                    //constraint end to parent end
                    end.linkTo(parent.end)

                    //constraint bottom to parent bottom
                    bottom.linkTo(parent.bottom)


            })
    }

}