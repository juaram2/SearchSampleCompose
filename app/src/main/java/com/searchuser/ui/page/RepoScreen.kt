package com.searchuser.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.searchuser.data.model.RepoItemModel
import com.searchuser.ui.component.NoData
import com.searchuser.ui.component.ProgressIndicator
import com.searchuser.viewmodel.RepoViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun RepoScreen(viewModel: RepoViewModel = hiltViewModel()) {
    RepoScreen(viewModel.repos)
}

@Composable
private fun RepoScreen(repos: Flow<PagingData<RepoItemModel>>) {
    val lazyRepos = repos.collectAsLazyPagingItems()

    val state = rememberLazyListState()

    LazyColumn(Modifier.fillMaxSize(), state = state) {
        items(lazyRepos) { repos ->
            repos?.let {
                RepoListItem(it)
            }
        }

        lazyRepos.apply {
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
private fun RepoListItem(repo: RepoItemModel) {
    Column {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = repo.name, fontWeight = FontWeight.Bold)
            repo.description?.let {
                Text(text = it, fontSize = 12.sp, color = Color.Gray)
            }
        }
        Divider()
    }
}