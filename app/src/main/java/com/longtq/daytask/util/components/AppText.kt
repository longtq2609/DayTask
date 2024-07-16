package com.longtq.daytask.util.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.longtq.daytask.R
import com.longtq.daytask.ui.theme.white

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    color: Color = white,
    textAlign: TextAlign = TextAlign.Start
) {
    val firSansFamily = FontFamily(
        Font(R.font.sf_pro_display_semibold, FontWeight.SemiBold),
        Font(R.font.sf_pro_display_regular, FontWeight.Normal),
        Font(R.font.sf_pro_display_medium, FontWeight.Medium),
        Font(R.font.pilat_extended_bold, FontWeight.Bold)
    )
    Text(
        textAlign = textAlign,
        modifier = modifier,
        text = text,
        color = color,
        fontFamily = firSansFamily,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}