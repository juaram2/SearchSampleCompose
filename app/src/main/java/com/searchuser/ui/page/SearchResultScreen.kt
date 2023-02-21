package com.searchuser.ui.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.searchuser.data.model.SearchItemModel
import com.searchuser.ui.component.NoData
import com.searchuser.ui.component.Photo
import com.searchuser.ui.component.ProgressIndicator
import com.searchuser.viewmodel.MainViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchResultScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    SearchResultScreen(navController, viewModel.users)
}

@Composable
private fun SearchResultScreen(
    navController: NavHostController,
    users: Flow<PagingData<SearchItemModel>>
) {
    val lazyUsers = users.collectAsLazyPagingItems()

    val state = rememberLazyListState()

    LazyColumn(Modifier.fillMaxSize(), state = state) {
        items(lazyUsers) { users ->
            users?.let {
                UserListItem(navController, it)
            }
        }

        lazyUsers.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { ProgressIndicator() }
                }
                loadState.append is LoadState.Loading -> {
                    item { ProgressIndicator() }
                }
                loadState.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        itemCount == 0 -> {
                    item { NoData() }
                }
            }
        }
    }
}

@Composable
private fun UserListItem(
    navController: NavHostController,
    user: SearchItemModel
) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("repo/${user.login}")
                }
                .padding(16.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Photo(user.avatarUrl)
                Text(text = user.login)
            }
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "arrow")
        }
        Divider()
    }
}