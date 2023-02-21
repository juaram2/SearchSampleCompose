package com.searchuser.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator() {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        CircularProgressIndicator(
            color = Color.Blue,
            modifier = Modifier
                .padding(45.dp)
                .size(45.dp)
        )
    }
}