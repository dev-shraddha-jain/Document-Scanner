package com.groot.documentscanner.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomTopAppBar(
    bgColor: Color,
    fgColor: Color,
    titleBarText: String,
    actionIcon: Int? = null,
    actionItemText: String? = null,
    navIcon: Int? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = titleBarText, modifier = Modifier.padding(start = 12.dp))
        },
        actions = {
            if (actionIcon != null) {
                Image(painter = painterResource(actionIcon), contentDescription = "")
            }
            if (actionItemText != null) {
                Text(text = actionItemText)
            }
            if (actionItemText != null || actionIcon != null) {
                Spacer(modifier = Modifier.padding(end = 12.dp))
            }
        },
        navigationIcon = {
            if (navIcon != null) {
                Image(painter = painterResource(navIcon), contentDescription = "")
            }
            if (actionItemText != null || actionIcon != null) {
                Spacer(modifier = Modifier.padding(start = 12.dp))
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = bgColor,
            titleContentColor = fgColor,
            navigationIconContentColor = fgColor,
            actionIconContentColor = fgColor,
        )
    )
}