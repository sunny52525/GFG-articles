package com.example.shimmeranimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shimmeranimation.ui.theme.ShimmerAnimationTheme
import com.example.shimmeranimation.ui.theme.ShimmerColorShades

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShimmerAnimationTheme(darkTheme = false) {
                Surface(color = MaterialTheme.colors.background) {

                    /**
                     * Lazy column as I am adding multiple items for display purpose
                     * create you UI according to requirement
                     */
                    LazyColumn {

                        /**
                         * Lay down the Shimmer Animated item 5 time
                         * [repeat] is like a loop which executes the body according to the number specified
                         */
                        repeat(5) {
                            item {
                                ShimmerAnimation()

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerAnimation(
) {

    /**
    Create InfiniteTransition
    which holds child animation like [Transition]
    animations start running as soon as they enter
    the composition and do not stop unless they are removed
     */
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        /**
        Specify animation positions,
        initial Values 0F means it starts from 0 position
         */
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(

            /**
             * Tween Animates between values over specified [durationMillis]
             */
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    /**
     * Create a gradient using the list of colors
     * Use Linear Gradient for animating in any direction according to requirement
     * start=specifies the position to start with in cartesian like system
     *       Offset(10f,10f) means x(10,0) , y(0,10)
     * end= Animate the end position to give the shimmer effect using the transition created above
     */
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    ShimmerItem(brush = brush)

}


@Composable
fun ShimmerItem(
    brush: Brush
) {

    /**
     * Column composable shaped like a rectangle,
     * set the [background]'s [brush] with the brush receiving from [ShimmerAnimation]
     * which will get animated.
     * Add few more Composable to test
     */
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(250.dp)
                .background(
                    brush = brush
                )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(vertical = 8.dp)
                .background(brush = brush)
        )
    }
}
