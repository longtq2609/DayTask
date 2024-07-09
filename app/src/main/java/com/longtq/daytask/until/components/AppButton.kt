package com.longtq.daytask.until.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.longtq.daytask.R
import com.longtq.daytask.ui.theme.black
import com.longtq.daytask.ui.theme.mainColor
import com.longtq.daytask.ui.theme.orangeYellow
import com.longtq.daytask.ui.theme.white

@Composable
fun AppButton(
    modifier: Modifier,
    textButton: String,
    onClick: () -> Unit,

    ) {
    Button(
        modifier = modifier.height(dimensionResource(id = R.dimen.dimen_64)),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = orangeYellow,
            contentColor = black
        ),
        shape = RoundedCornerShape(0)
    ) {
        AppText(
            text = textButton,
            fontWeight = FontWeight.SemiBold,
            fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp,
            color = black
        )
    }

}

@Composable
fun AppButtonGoogle(
    modifier: Modifier,
    onClick: () -> Unit,

    ) {
    Button(
        modifier = modifier.height(dimensionResource(id = R.dimen.dimen_64)),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = mainColor,
            contentColor = white
        ),
        shape = RoundedCornerShape(0),
        border = BorderStroke(dimensionResource(id = R.dimen.dimen_1), color = white)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_login_google),
                contentDescription = null,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dimen_8)))
            AppText(
                text = stringResource(id = R.string.title_google_login),
                fontWeight = FontWeight.Medium,
                fontSize = dimensionResource(id = R.dimen.dimen_18).value.sp
            )
        }
    }

}
