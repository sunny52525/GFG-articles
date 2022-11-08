package com.sunny.composeview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

class MainActivity : AppCompatActivity() {
    lateinit var composeView: ComposeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        composeView = findViewById(R.id.compose_view)
        composeView.setContent {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Greeting("Hello Geeks for geeks")
            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(
        text = "$text!", color = Color(0xFF0F9D58), fontStyle = FontStyle.Italic, fontSize = 30.sp
    )
}