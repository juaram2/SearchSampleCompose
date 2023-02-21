package com.searchuser.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.searchuser.api.SearchService
import com.searchuser.data.model.RepoItemModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepoRepository @Inject constructor(private val service: SearchService) {
    fun getUserRepoStream(username: String): Flow<PagingData<RepoItemModel>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { RepoPagingSource(service, username) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}