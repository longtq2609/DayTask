package com.longtq.daytask.screen.addmember

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.longtq.daytask.R
import com.longtq.daytask.activity.MainViewModel
import com.longtq.daytask.screen.addmember.components.MemberList
import com.longtq.daytask.ui.theme.fordBlue
import com.longtq.daytask.ui.theme.lightTeal
import com.longtq.daytask.ui.theme.mainColor
import com.longtq.daytask.util.components.AppButton
import com.longtq.daytask.util.components.AppTextField
import com.longtq.daytask.util.components.AppTopBar
import com.longtq.daytask.util.components.LoadingDialog
import com.longtq.domain.entity.User

@Composable
fun AddMemberScreen(
    onBack: () -> Unit,
    mainViewModel: MainViewModel
) {
    val viewModel: AddMemberViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()

    AddMemberView(state = state, onChangeSearchUser = {
        viewModel.onSearchUserChanged(it)
    },
        onBack = { onBack.invoke() },
        onUpdateMember = {
            viewModel.updateMember(it)
        }, mainViewModel = mainViewModel, onGetMember = {
            viewModel.getAllUsers(it)
        })
}

@Composable
fun AddMemberView(
    mainViewModel: MainViewModel,
    onGetMember: (List<User>) -> Unit,
    state: State<AddMemberViewState>,
    onChangeSearchUser: (String) -> Unit,
    onUpdateMember: (User) -> Unit,
    onBack: () -> Unit
) {
    val searchUser = remember { mutableStateOf(TextFieldValue("")) }

    LoadingDialog(isLoading = state.value.isLoading)

    LaunchedEffect(key1 = mainViewModel.listMember) {
        onGetMember(mainViewModel.listMember.value)
    }

    Scaffold(
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_16)),
        containerColor = mainColor,
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.title_add_member),
                onBack = { onBack.invoke() },
                modifier = Modifier
                    .systemBarsPadding()
                    .navigationBarsPadding(),
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .background(mainColor)
                    .fillMaxSize()
            ) {
                Row {
                    AppTextField(modifier = Modifier
                        .padding(bottom = dimensionResource(id = R.dimen.dimen_16))
                        .background(fordBlue)
                        .height(dimensionResource(id = R.dimen.dimen_48)),
                        fontWeight = FontWeight.Normal,
                        fontSize = dimensionResource(id = R.dimen.dimen_16).value.sp,
                        value = searchUser.value.text,
                        onValueChange = { input ->
                            searchUser.value = TextFieldValue(input)
                            onChangeSearchUser(input)
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.title_search_member),
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

                }
                MemberList(
                    onMemberChecked = { updatedUser ->
                        onUpdateMember(updatedUser)
                    },
                    users = state.value.listUser, modifier = Modifier
                        .weight(1f)
                        .padding(
                            bottom = dimensionResource(
                                id = R.dimen.dimen_16
                            )
                        )
                )
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    textButton = stringResource(id = R.string.title_add_member)
                ) {
                    val listUserSelected =
                        state.value.listUser.filter { user -> user.isChecked == true }
                    mainViewModel.setListMemberSelected(listUserSelected)
                    onBack.invoke()
                }
            }

        })
}


