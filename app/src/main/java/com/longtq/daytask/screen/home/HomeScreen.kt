package com.longtq.daytask.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.longtq.daytask.R
import com.longtq.daytask.ui.theme.fordBlue
import com.longtq.daytask.ui.theme.lightTeal
import com.longtq.daytask.ui.theme.mainColor
import com.longtq.daytask.ui.theme.orangeYellow
import com.longtq.daytask.util.components.AppText
import com.longtq.daytask.util.components.AppTextField
import com.longtq.daytask.util.components.LoadingDialog

@Composable
fun HomeScreen(
    onNavigationToProfile: () -> Unit,
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    HomeView(state = state, onSearchTextChanged = {
        viewModel.onSearchTextChanged(it)
    }, onNavigationToProfile = onNavigationToProfile)
}

@Composable
fun HomeView(
    onSearchTextChanged: (String) -> Unit,
    onNavigationToProfile: () -> Unit,
    state: State<HomeViewState>
) {

    val searchText = remember { mutableStateOf(TextFieldValue("")) }

    LoadingDialog(isLoading = state.value.isLoading)


    Column(modifier = Modifier
        .background(mainColor)
        .padding(dimensionResource(id = R.dimen.dimen_16))
        .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_24))
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                AppText(
                    text = stringResource(id = R.string.title_welcome_back),
                    fontWeight = FontWeight.Medium,
                    fontSize = dimensionResource(
                        id = R.dimen.dimen_12
                    ).value.sp,
                    color = orangeYellow
                )
                AppText(
                    text = "Trương Quang Long",
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(
                        id = R.dimen.dimen_22
                    ).value.sp
                )
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(orangeYellow, CircleShape)
                    .padding(8.dp)
                    .clickable {
                        onNavigationToProfile.invoke()
                    },
                contentAlignment = Alignment.Center
            )
            {
                Icon(painter = painterResource(id = R.drawable.ic_user), contentDescription = null)
            }

        }

        Row {

            AppTextField(modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.dimen_16))
                .background(fordBlue)
                .weight(1f)
                .height(dimensionResource(id = R.dimen.dimen_48)),
                fontWeight = FontWeight.Normal,
                fontSize = dimensionResource(id = R.dimen.dimen_16).value.sp,
                value = searchText.value.text,
                onValueChange = {
                    searchText.value = TextFieldValue(it)
                    onSearchTextChanged(it)
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.title_search_tasks),
                        fontWeight = FontWeight.Normal,
                        fontSize = dimensionResource(
                            id = R.dimen.dimen_16
                        ).value.sp,
                        color = lightTeal
                    )
                },

                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = lightTeal
                    )
                }
            )

            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.dimen_48))
                    .background(orangeYellow)
                    .padding(dimensionResource(id = R.dimen.dimen_8)),
                contentAlignment = Alignment.Center
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filtter),
                    contentDescription = null
                )
            }
        }
    }

}

@Preview
@Composable
fun HomeViewPreview() {
    HomeView(
        state = remember { mutableStateOf(HomeViewState()) },
        onSearchTextChanged = {},
        onNavigationToProfile = {})
}