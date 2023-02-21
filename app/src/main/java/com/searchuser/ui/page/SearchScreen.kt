package com.searchuser.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun SearchScreen(navController: NavHostController) {
    val query = remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        TextField(
            value = query.value,
            onValueChange = {
                query.value = it
            },
            singleLine = true,
            placeholder = {
              Text("Search users", color = Color.Gray)
            },
            trailingIcon = {
                IconButton(onClick = {
                    navController.navigate("search/${query.value}")
                }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            },
            keyboardActions = KeyboardActions(onDone = {
                navController.navigate("search/${query.value}")
            })
        )
    }
}
