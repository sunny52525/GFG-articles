package com.shaun.bounceanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.shaun.bounceanimation.ui.theme.BounceAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BounceAnimationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Bounce()
                }
            }
        }
    }
}


enum class BounceState { Pressed, Released }


@Preview(showBackground = true)
@Composable
fun Bounce() {

    /**
     * Using remember to store the current State,
     * remember will only be evaluated during the composition.
     */
    var currentState: BounceState by remember { mutableStateOf(BounceState.Released) }


    /**
     * Setting up the [Transition], and update it with the target provided by [targetState]
     */
    val transition = updateTransition(targetState = currentState, label = "animation")


    /**
     * [animateFloat] creates a float animation given by the transition,
     * transition also handles the state of animation
     */
    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) }, label = ""
    ) { state ->


        // When the item is pressed ,reduce its size by 5% or make its size 0.95 of its original size
        // Change this value to see effect
        if (state == BounceState.Pressed) {
            0.95f
        } else {
            //When the item is released , make it of its original size
            1f
        }
    }


    //Basic compose Box Layout
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(modifier = Modifier

            /**
             *[pointerInput] modifier handles the various pointer related gestures,
             * this animation only needs [detectTapGestures] to detect when the item is tapped
             * then change the current state (value of currentState) to appropriate value.
             */
            .pointerInput(Unit) {
                detectTapGestures(onPress = {

                    //Set the currentState to Pressed to trigger Pressed animation
                    currentState = BounceState.Pressed


                    //Waits for the tap to release before returning the call
                    tryAwaitRelease()

                    //Set the currentState to Release to trigger Release animation
                    currentState = BounceState.Released
                })
            }) {
            Image(
                painter = painterResource(id = R.drawable.gfg),
                contentDescription = "gfg",


                /**
                 * Change the scale of Image using graphicsLayer modifier, lambda contains
                 * various property including scale,alpha,translation,shadowElevation,rotation and more.
                 * This animation,only needs the scale property,assigning the scaleX,scaleY to same scale Animation
                 * created above. So ,when pointerInput changes currentState, scale changes and animation is played
                 *
                 * Note:You can create two transition instead of only one,lets say scalex,scaley to animated
                 * X and Y of Item to animate differently instead of using same size.
                 */
                modifier = Modifier.graphicsLayer {
                    scaleX = scale
                    scaleY = scale

                })

        }

    }
}