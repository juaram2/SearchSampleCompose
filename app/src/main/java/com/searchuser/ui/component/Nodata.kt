package com.searchuser.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoData() {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(150.dp, 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Default.Search,
                contentDescription = "search",
                modifier = Modifier
                    .size(60.dp, 60.dp)
                    .padding(bottom = 24.dp),
                tint = Color.Gray
            )
            Text(text = "No Data", fontSize = 20.sp, color = Color.Gray)
        }
    }
}