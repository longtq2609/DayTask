package com.longtq.daytask.screen.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.longtq.daytask.R
import com.longtq.daytask.screen.create.components.MemberList
import com.longtq.daytask.screen.create.components.TimePickerDialog
import com.longtq.daytask.ui.theme.fordBlue
import com.longtq.daytask.ui.theme.mainColor
import com.longtq.daytask.ui.theme.orangeYellow
import com.longtq.daytask.util.components.AppButton
import com.longtq.daytask.util.components.AppText
import com.longtq.daytask.util.components.AppTextField
import com.longtq.daytask.util.components.AppTopBar
import com.longtq.daytask.util.components.LoadingDialog

@Composable
fun CreateTaskScreen(
    onBackClick: () -> Unit,

) {
    val viewModel: CreateTaskViewModel = hiltViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    CreateTaskView(
        state = state,
        onBackClick = onBackClick,
        onChangeTitleText = { viewModel.onChangeTitleText(it) },
        onChangeTaskDetail = {
            viewModel.onChangeTaskDetail(it)
        },
        onRemoveMember = {
            viewModel.removeMember(it)
        },
        timeNow = state.value.timeNow,
        dateNow = state.value.dateNow,
        onCreateTask = {
            viewModel.onCreateTask()
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskView(
    onBackClick: () -> Unit,
    onChangeTitleText: (String) -> Unit,
    onChangeTaskDetail: (String) -> Unit,
    onRemoveMember: (String) -> Unit,
    onCreateTask: () -> Unit,
    timeNow: String = "",
    dateNow: String = "",
    state: State<CreateTaskViewState>
) {
    val titleTask = remember { mutableStateOf(TextFieldValue("")) }
    val taskDetail = remember { mutableStateOf(TextFieldValue("")) }

    var timePickerState = rememberTimePickerState()
    var showTimePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    LoadingDialog(isLoading = state.value.isLoading)
    Scaffold(
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dimen_16)),
        containerColor = mainColor,
        topBar = {
            AppTopBar(
                modifier = Modifier
                    .systemBarsPadding()
                    .navigationBarsPadding(),
                title = stringResource(id = R.string.title_create_task),
                onBack = { onBackClick.invoke() })
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(mainColor)
            ) {
                AppText(
                    text = stringResource(id = R.string.title_task),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = dimensionResource(
                        id = R.dimen.dimen_20
                    ).value.sp,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_16)),

                    )

                AppTextField(
                    value = titleTask.value.text, onValueChange = { input ->
                        titleTask.value = TextFieldValue(input)
                        onChangeTitleText(input)
                    },
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_16)),
                    fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp,
                    fontWeight = FontWeight.Normal

                )

                AppText(
                    text = stringResource(id = R.string.title_task_detail),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = dimensionResource(
                        id = R.dimen.dimen_20
                    ).value.sp,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_16))
                )

                AppTextField(
                    value = taskDetail.value.text, onValueChange = { input ->
                        taskDetail.value = TextFieldValue(input)
                        onChangeTaskDetail(input)
                    },
                    modifier = Modifier
                        .padding(bottom = dimensionResource(id = R.dimen.dimen_16))
                        .height(
                            dimensionResource(id = R.dimen.dimen_100)
                        ),
                    fontSize = dimensionResource(id = R.dimen.dimen_11).value.sp,
                    fontWeight = FontWeight.Normal
                )
                AppText(
                    text = stringResource(id = R.string.title_add_team),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = dimensionResource(
                        id = R.dimen.dimen_20
                    ).value.sp,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_16))

                )

                MemberList(users = state.value.listUsers,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_16)),
                    onRemoveMember = { idUser ->
                        onRemoveMember(idUser)
                    },
                    onAddMember = {

                    })

                AppText(
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_16)),
                    text = stringResource(id = R.string.title_time_date),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = dimensionResource(
                        id = R.dimen.dimen_20
                    ).value.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Row(
                        modifier = Modifier
                            .weight(1F)
                            .clickable {
                                showTimePicker = true
                            }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.dimen_40))
                                .background(orangeYellow)

                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clock),
                                contentDescription = "choose time"
                            )


                        }
                        Box(
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen.dimen_40))
                                .weight(1f)
                                .background(fordBlue),
                            contentAlignment = Alignment.Center,
                        ) {
                            AppText(
                                modifier = Modifier
                                    .background(fordBlue),
                                textAlign = TextAlign.Center,
                                text = timeNow,
                                fontWeight = FontWeight.Medium,
                                fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp
                            )
                        }

                    }
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dimen_8)))
                    Row(
                        modifier = Modifier
                            .weight(1F)
                            .clickable {
                                showDatePicker = true
                            },
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(dimensionResource(id = R.dimen.dimen_40))
                                .background(orangeYellow)

                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_calendar_mini),
                                contentDescription = "choose date"
                            )


                        }
                        Box(
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen.dimen_40))
                                .weight(1f)
                                .background(fordBlue),
                            contentAlignment = Alignment.Center,
                        ) {
                            AppText(
                                modifier = Modifier.background(fordBlue),
                                textAlign = TextAlign.Center,
                                text = dateNow,
                                fontWeight = FontWeight.Medium,
                                fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp
                            )
                        }

                    }
                }
                AppButton(
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.dimen_32))
                        .fillMaxWidth(),
                    onClick = {
                        onCreateTask.invoke()
                    },
                    textButton = stringResource(id = R.string.title_create)
                )
            }
        }
    )
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            DatePicker(state = datePickerState)
        }
    }

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            TimePicker(state = timePickerState)
        }
    }

}

@Preview
@Composable
fun CreateTaskViewPreview() {
    CreateTaskView(
        state = remember { mutableStateOf(CreateTaskViewState()) },
        onBackClick = {},
        onChangeTitleText = {},
        onChangeTaskDetail = {},
        onRemoveMember = {},
        onCreateTask = {})
}