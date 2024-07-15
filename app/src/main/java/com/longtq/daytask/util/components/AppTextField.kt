package com.longtq.daytask.util.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.longtq.daytask.R
import com.longtq.daytask.ui.theme.fordBlue
import com.longtq.daytask.ui.theme.white

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = TextUnit.Unspecified,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val firSansFamily = FontFamily(
        Font(R.font.sf_pro_display_semibold, FontWeight.SemiBold),
        Font(R.font.sf_pro_display_regular, FontWeight.Normal),
        Font(R.font.sf_pro_display_medium, FontWeight.Medium),
    )
    TextField(
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = fordBlue,
            focusedContainerColor = fordBlue,
            disabledContainerColor = fordBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        textStyle = TextStyle(
            color = white, fontFamily = firSansFamily,
            fontWeight = fontWeight,
            fontSize = fontSize
        ),
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .background(fordBlue),
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
        isError = isError,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
    )
}


@Preview
@Composable
fun TextFieldBasePreview() {
    AppTextField(
        leadingIcon = { Icon(imageVector = Icons.Filled.Call, contentDescription = null) },
        value = "",
        onValueChange = {},
    )

}