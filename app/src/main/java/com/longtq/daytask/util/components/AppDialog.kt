package com.longtq.daytask.util.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.longtq.daytask.R

@Composable
fun LoadingDialog(isLoading: Boolean) {
    if (isLoading) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {
            AnimatedLoading()
        }
    }
}

@Composable
fun AnimatedLoading(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_loading))
    LottieAnimation(
        modifier = modifier
            .width(dimensionResource(id = R.dimen.dimen_80))
            .height(dimensionResource(id = R.dimen.dimen_80)),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}


@Composable
fun CustomDialog(title: String, message: String, setShowDialog: (Boolean) -> Unit) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_16)),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_20))) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )

                    }

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_20)))
                    Text(
                        text = message,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_20)))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_50)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(id = R.dimen.dimen_50))
                        ) {
                            Text(text = "Done")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    CustomDialog("TiTle", "Message") {}
}
