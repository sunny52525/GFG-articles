package com.gfg.canvasapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gfg.canvasapi.ui.theme.CanvasAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          GFGIcon()
        }
    }
}

@Preview
@Composable
fun GFGIcon() {
    CanvasAPITheme {
        //Create a box to and set contentAlignment to Center to  center the Icon
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF308D46)),

            contentAlignment = Alignment.Center
        ) {

            //Create a row
            Row {

                //Place one Icon in the row but rotate Y Axis by -180 degree
                // it will look like mirror image
                Icon(Modifier.graphicsLayer(rotationY = -180f))
                //set some space between the icons
                Spacer(modifier = Modifier.width(20.dp))

                // Original Icon without rotation
                Icon()

            }
        }
    }
}

@Composable
fun Icon(modifier: Modifier = Modifier) {

    /**
     * Draw the Canvas Composable
     */
    Canvas(modifier = modifier.size(100.dp), onDraw = {
        /**
         * Canvas scope provides width and height of the canvas
         * save height and width of canvas in a variable to use in the code
         */
        val canvasWidth = size.width
        val canvasHeight = size.height

        //we first draw the arc which will be the curve of the logo
        drawArc(
            color = Color.White,
            //arc starts at 0 degree and ends
            startAngle = 0f,
            //set use center to false to draw the arc without centered line
            //Tip: use center to true to draw the arc with centered line and see the difference
            useCenter = false,
            //set the end angle of the arc
            sweepAngle = 290f,
            //set the width of the arc and how the arc cap will be drawn
            //  cap = StrokeCap.Round will draw the arc with rounded end
            style = Stroke(width = 40f, cap = StrokeCap.Square)
        )

        //draw the center line of the logo
        drawLine(

            color = Color.White,
            //set the start point of the line to the center of the canvas
            start = Offset(x = 0f, y = canvasHeight / 2),
            //set the end point of the line to the center of the canvas
            end = Offset(x = canvasWidth, y = canvasHeight / 2),
            //set the width of the line
            strokeWidth = 40f
        )

    })

}
