package com.longtq.daytask.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.longtq.daytask.R
import com.longtq.daytask.ui.theme.mainColor
import com.longtq.daytask.util.components.AppTopBar
import com.longtq.daytask.util.components.LoadingDialog

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit
) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    ProfileView(state = state, onBackClick = onBackClick)
}

@Composable
fun ProfileView(
    state: State<ProfileViewState>,
    onBackClick: () -> Unit
) {
    LoadingDialog(isLoading = state.value.isLoading)
    Scaffold(
        modifier = Modifier.background(mainColor),
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.title_profile),
                onBack = { onBackClick.invoke() })
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(mainColor)
        ) {

        }

    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onBackClick = {})
}