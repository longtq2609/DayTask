package com.longtq.daytask.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.longtq.daytask.R
import com.longtq.daytask.ui.theme.white
import com.longtq.daytask.util.components.AppButton
import com.longtq.daytask.util.components.LoadingDialog

@Composable
fun SplashScreen(
    onNavigationToLogin: () -> Unit
) {
    val viewModel: SplashViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    SplashView(state = state, onNavigationToLogin = onNavigationToLogin)
}

@Composable
fun SplashView(
    onNavigationToLogin: () -> Unit = {},
    state: State<SplashViewState>
) {
    LoadingDialog(isLoading = state.value.isLoading)

    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.dimen_16))
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.dimen_32),
                    bottom = dimensionResource(id = R.dimen.dimen_16)
                ),
            contentAlignment = Alignment.TopStart
        ) {
            AsyncImage(
                model = R.drawable.icon_logo_day_task,
                contentDescription = null,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.dimen_93))
                    .height(dimensionResource(id = R.dimen.dimen_62)),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(white)
                .width(dimensionResource(id = R.dimen.dimen_369))
                .height(dimensionResource(id = R.dimen.dimen_330)),
            contentAlignment = Alignment.TopStart,
        ) {
            AsyncImage(
                model = R.drawable.img_pana,
                contentDescription = null,
                modifier = Modifier
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.dimen_32)),
            contentAlignment = Alignment.TopStart
        ) {
            AsyncImage(
                model = R.drawable.img_content_splash,
                contentDescription = null,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.dimen_376))
                    .height(dimensionResource(id = R.dimen.dimen_240)),
            )
        }

        AppButton(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.dimen_32))
                .fillMaxWidth(),
            onClick = { onNavigationToLogin.invoke() },
            textButton = stringResource(id = R.string.title_start)
        )
    }
}

@Preview
@Composable
fun SplashViewPreview() {
    SplashView(state = remember { mutableStateOf(SplashViewState()) })
}