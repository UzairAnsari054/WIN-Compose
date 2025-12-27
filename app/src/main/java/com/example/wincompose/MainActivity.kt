package com.example.wincompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.wincompose.ui.theme.WINComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WINComposeTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {

    Log.i("MainScreen", "MainScreen Invoked")

    var counter by rememberSaveable { mutableIntStateOf(0) }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                counter++
                Log.i("MainScreen", "$counter")
            }) {
                Text(text = "Increase")
            }

            Text(text = "Counter Value: $counter")

            Button(onClick = {
                counter--
                Log.i("MainScreen", "$counter")
            }) {
                Text(text = "Decrease")
            }
        }
    }
}

// So MutableState of tells the composable, "Hey, i am a state and my value just changed, so you need to recompose and re-draw the ui with the update value"
// While the role of remember is to hold that updated value so that it can used in the newly re-drawn ui
// that's why we have them together, remember {mutableStateOf(initialValue)}

