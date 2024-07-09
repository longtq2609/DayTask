package com.longtq.daytask

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.longtq.daytask.navigation.AppNavigation
import com.longtq.daytask.ui.theme.DayTaskTheme
import com.longtq.daytask.ui.theme.mainColor
import com.longtq.daytask.until.components.CustomDialog
import com.longtq.daytask.util.Event
import com.longtq.daytask.util.EventBus
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
            val showDialog = remember { mutableStateOf(false) }
            val titleDialog = remember { mutableStateOf("") }
            val messageDialog = remember { mutableStateOf("") }

            LaunchedEffect(key1 = lifecycleOwner) {
                EventBus.events.collect { event ->
                    when (event) {
                        is Event.Toast -> {
                            Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is Event.ShowDialog -> {
                            titleDialog.value = event.title
                            messageDialog.value = event.message
                            showDialog.value = true
                        }
                    }
                }
            }
            DayTaskTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = mainColor
                ) {
                    AppNavigation()
                    if (showDialog.value) {
                        CustomDialog(
                            title = titleDialog.value,
                            message = messageDialog.value
                        ) {
                            showDialog.value = it
                        }
                    }

                }
            }
        }
    }
}
