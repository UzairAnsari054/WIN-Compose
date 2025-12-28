package com.example.wincompose

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

suspend fun startCounter(
    increment: () -> Unit,
    onCounterEnd: () -> Unit
) {
    for (i in 1..5) {
        delay(1000)
        increment()
    }
    onCounterEnd()
}


@Composable
fun MainScreen() {

    Log.i("MainScreen", "MainScreen Invoked")

    var counter by remember { mutableIntStateOf(0) }
    var counterMsg by remember { mutableStateOf("NOT STARTED YET") }

    val counterBgColor by remember(counter) {
        derivedStateOf {
            if (counter?.rem(2) == 0) Color.Green else Color.Red
        }
    }

    val increment = {
        counter++
        counterMsg = "COUNTER IS NOW----------------: $counter"
    }

    val context = LocalContext.current

    DisposableEffect(key1 = true) {
        onDispose {
            Log.i("DisposableEffect", "onDisposes Called")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        ChildComposable(
            counterMsg = counterMsg,
            context = context,
            increment = increment
        )

        Text(
            text = "Counter Value: $counter",
            modifier = Modifier
                .background(counterBgColor)
                .padding(20.dp)

        )
    }
}

@Composable
fun ChildComposable(
    counterMsg: String,
    context: Context,
    increment: () -> Unit
) {
    val updatedCounterMsg by rememberUpdatedState(newValue = counterMsg)
    Log.i("MainScreen", "OUTSIDE LaunchedEffect: OOOOOOOOOOOOOOO$updatedCounterMsg")
    LaunchedEffect(key1 = true) {
        Log.i("MainScreen", "Inside LaunchedEffect Invoked: IIIIIIIIIIIII $updatedCounterMsg")
        startCounter(
            increment = { increment() },
            onCounterEnd = {
                Log.i("MainScreen", "Count Completed $updatedCounterMsg")
                Toast.makeText(context, "Count Completed", Toast.LENGTH_LONG).show()
            }
        )
    }
}

// So MutableState of tells the composable, "Hey, i am a state and my value just changed, so you need to recompose and re-draw the ui with the update value"
// While the role of remember is to hold that updated value so that it can used in the newly re-drawn ui
// that's why we have them together, remember {mutableStateOf(initialValue)}

