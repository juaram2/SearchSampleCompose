package com.searchuser.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter


@OptIn(ExperimentalCoilApi::class)
@Composable
fun Photo(url: String) {
    val photo = rememberImagePainter(data = url)

    Box {
        when (photo.state) {
            is ImagePainter.State.Loading -> {
                Box(modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp)
                    .background(Color.LightGray))
            }
            else -> {
                Box {}
            }
        }

        Image(painter = photo, contentDescription = "photo",
            Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(end = 10.dp))
    }
}