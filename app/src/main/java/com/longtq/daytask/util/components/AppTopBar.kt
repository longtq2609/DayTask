package com.longtq.daytask.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.longtq.daytask.R
import com.longtq.daytask.ui.theme.mainColor
import com.longtq.daytask.ui.theme.white


@Composable
fun AppTopBar(
    title: String,
    onBack: () -> Unit,
    isShowIcon: Boolean = false,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(mainColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onBack.invoke() }) {
            Icon(
                tint = white,
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = "back"
            )
        }
        AppText(
            modifier = Modifier.weight(1f),
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = dimensionResource(id = R.dimen.dimen_20).value.sp,
            textAlign = TextAlign.Center
        )
        if (isShowIcon) {
            IconButton(
                onClick = { onBack.invoke() }) {
                Icon(
                    tint = white,
                    painter = painterResource(R.drawable.ic_password),
                    contentDescription = "back"
                )
            }
        } else {
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dimen_48)))
        }

    }
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTopBar(title = stringResource(id = R.string.title_sign_up), onBack = {})
}
