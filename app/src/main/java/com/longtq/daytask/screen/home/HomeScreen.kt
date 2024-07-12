package com.longtq.daytask.screen.home

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
fun HomeScreen(

) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    HomeView(state = state)
}

@Composable
fun HomeView(
    state: State<HomeViewState>
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(black)) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Home",
            color = white
        )// Make sure it fills the screen
    }
}

@Preview
@Composable
fun HomeViewPreview() {
    HomeView(state = remember { mutableStateOf(HomeViewState()) })
}