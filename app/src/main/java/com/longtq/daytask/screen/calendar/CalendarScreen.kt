package com.longtq.daytask.screen.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.longtq.daytask.ui.theme.black
import com.longtq.daytask.ui.theme.white

@Composable
fun CalendarScreen(

) {
    val viewModel: CalendarViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    CalendarView(state = state)
}

@Composable
fun CalendarView(
    state: State<CalendarViewState>
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(black)) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = " Calendar",
            color = white
        )// Make sure it fills the screen
    }
}

@Preview
@Composable
fun CalendarViewPreview() {
    CalendarView(state = remember { mutableStateOf(CalendarViewState()) })
}