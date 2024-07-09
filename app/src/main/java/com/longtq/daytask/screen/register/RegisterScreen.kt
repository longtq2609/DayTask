package com.longtq.daytask.screen.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.longtq.daytask.R
import com.longtq.daytask.ui.theme.baseTextColor
import com.longtq.daytask.ui.theme.orangeYellow
import com.longtq.daytask.ui.theme.white
import com.longtq.daytask.until.components.AppButton
import com.longtq.daytask.until.components.AppButtonGoogle
import com.longtq.daytask.until.components.AppText
import com.longtq.daytask.until.components.AppTextField
import com.longtq.daytask.until.components.LoadingDialog

@Composable
fun RegisterScreen(
    onBackPress: () -> Unit,
) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    RegisterView(state = state,
        onBackPress = { onBackPress() },
        onClickRegister = { viewModel.onClickRegister() },
        onChangeFullName = { viewModel.onChangeFullName(it) },
        onChangeEmailAddress = { viewModel.onChangeEmailAddress(it) },
        onChangePassword = { viewModel.onChangePassword(it) },
        onClickLoginGoogle = { viewModel.onClickLoginGoogle() })
}

@Composable
fun RegisterView(
    state: State<RegisterViewState>,
    onClickRegister: () -> Unit,
    onChangeFullName: (String) -> Unit,
    onClickLoginGoogle: () -> Unit,
    onChangeEmailAddress: (String) -> Unit,
    onChangePassword: (String) -> Unit,
    onBackPress: () -> Unit
) {

    val fullName = remember { mutableStateOf(TextFieldValue("")) }
    val email = remember { mutableStateOf(TextFieldValue("")) }
    val password = remember { mutableStateOf(TextFieldValue("")) }
    val showPassword = remember { mutableStateOf(false) }

    LoadingDialog(isLoading = state.value.isLoading)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_26)),
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.dimen_32)),
            contentAlignment = Alignment.TopCenter
        ) {
            AsyncImage(
                model = R.drawable.icon_logo_day_task,
                contentDescription = null,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.dimen_139))
                    .height(dimensionResource(id = R.dimen.dimen_92)),
            )
        }

        AppText(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_48)),
            text = stringResource(id = R.string.title_create_account),
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResource(id = R.dimen.dimen_26).value.sp
        )

        AppText(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_16)),
            text = stringResource(id = R.string.title_full_name),
            fontWeight = FontWeight.Normal,
            fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp,
            color = baseTextColor
        )


        AppTextField(

            fontWeight = FontWeight.Normal,
            fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.dimen_16)),
            leadingIcon = {
                Icon(
                    tint = white,
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null
                )
            },
            value = fullName.value.text,
            onValueChange = {
                fullName.value = TextFieldValue(it)
                onChangeFullName(it)
            },

            singleLine = true
        )


        AppText(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_16)),
            text = stringResource(id = R.string.title_email_address),
            fontWeight = FontWeight.Normal,
            fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp,
            color = baseTextColor
        )


        AppTextField(

            fontWeight = FontWeight.Normal,
            fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.dimen_16)),
            leadingIcon = {
                Icon(
                    tint = white,
                    painter = painterResource(id = R.drawable.ic_user_tag),
                    contentDescription = null
                )
            },
            value = email.value.text,
            onValueChange = {
                email.value = TextFieldValue(it)
                onChangeEmailAddress(it)
            },

            singleLine = true
        )

        AppText(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_16)),
            text = stringResource(id = R.string.title_password),
            fontWeight = FontWeight.Normal,
            fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp,
            color = baseTextColor
        )

        AppTextField(
            fontWeight = FontWeight.Normal,
            fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp,
            trailingIcon = {
                if (showPassword.value) {
                    IconButton(onClick = { showPassword.value = false }) {
                        Icon(
                            tint = white,
                            painter = painterResource(id = R.drawable.ic_hide_eyes),
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword.value = true }) {
                        Icon(
                            tint = white,
                            painter = painterResource(id = R.drawable.ic_show_eyes),
                            contentDescription = "show_password"
                        )
                    }
                }
            },
            visualTransformation = if (showPassword.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.dimen_16)),
            leadingIcon = {
                Icon(
                    tint = white,
                    painter = painterResource(id = R.drawable.ic_password),
                    contentDescription = null
                )
            },

            value = password.value.text,
            onValueChange = {
                password.value = TextFieldValue(it)
                onChangePassword(it)
            },
            singleLine = true
        )


//        Checkbox(checked = true, onCheckedChange = {
//
//        })

        AppButton(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.dimen_32))
                .fillMaxWidth(),
            onClick = {
                onClickRegister.invoke()
            },
            textButton = stringResource(id = R.string.title_sign_up)
        )
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = dimensionResource(id = R.dimen.dimen_32)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Divider(
                modifier = Modifier.width(dimensionResource(id = R.dimen.dimen_112)),
                color = baseTextColor,
            )
            AppText(
                text = stringResource(id = R.string.title_login_more), color = baseTextColor,
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.dimen_8),
                    end = dimensionResource(id = R.dimen.dimen_8)
                ),
                fontSize = dimensionResource(id = R.dimen.dimen_16).value.sp,
                fontWeight = FontWeight.Medium
            )
            Divider(
                modifier = Modifier.width(dimensionResource(id = R.dimen.dimen_112)),
                color = baseTextColor,
            )
        }

        AppButtonGoogle(
            onClick = onClickLoginGoogle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.dimen_32)),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(id = R.dimen.dimen_16)),
            horizontalArrangement = Arrangement.Center,
        ) {
            AppText(
                text = stringResource(id = R.string.title_have_account), color = baseTextColor,
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.dimen_8)),
                fontSize = dimensionResource(id = R.dimen.dimen_16).value.sp,
                fontWeight = FontWeight.Medium
            )
            AppText(
                text = stringResource(id = R.string.button_text_login), color = orangeYellow,
                modifier = Modifier.clickable {
                    onBackPress.invoke()
                },
                fontSize = dimensionResource(id = R.dimen.dimen_16).value.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onBackPress = {}
    )
}