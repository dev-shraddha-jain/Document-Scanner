package com.groot.documentscanner.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp


@Composable
fun ButtonView(
    text: String,
    topBgColor: Color,
    fgColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        content = {
            Text(text = text)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = topBgColor,
            contentColor = fgColor,
        )
    )
}

@Composable
fun BottomButtonView(
    text: String,
    topBgColor: Color,
    fgColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .height(65.dp)
            .fillMaxWidth(),
        shape = RectangleShape,
        content = {
            Text(text)
        },
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = topBgColor,
            contentColor = fgColor,
        )
    )
}