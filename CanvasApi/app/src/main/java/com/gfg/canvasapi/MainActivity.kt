package com.gfg.canvasapi

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gfg.canvasapi.ui.theme.CanvasAPITheme
import com.gfg.canvasapi.ui.theme.GFGGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CanvasAPITheme {
                Surface(color = MaterialTheme.colors.background) {
                    GFGIcon()
                }
            }
        }
    }
}

@Composable
fun GFGIcon() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GFGGreen),
        contentAlignment = Alignment.Center
    ) {
        Row() {
            Icon(Modifier.graphicsLayer {
                rotationY=-180f
            })
            Spacer(modifier = Modifier.width(15.dp))
            Icon()

        }
    }
}

@Composable
fun Icon(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(100.dp), onDraw = {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawArc(
            color = Color.White,
            startAngle = 0f,
            useCenter = true,
            sweepAngle = 310f,
            style = Stroke(width = 54f, cap = StrokeCap.Round)
        )
        drawCircle(
            color = GFGGreen,
            radius = 110f
        )
        drawLine(
            color = Color.White,
            start = Offset(x = 0f, y = canvasHeight / 2),
            end = Offset(x = canvasWidth, y = canvasHeight / 2),
            strokeWidth = 54f
        )

    })

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CanvasAPITheme {
        GFGIcon()
    }
}