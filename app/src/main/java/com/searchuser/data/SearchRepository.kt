package com.searchuser.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.searchuser.api.SearchService
import com.searchuser.data.model.SearchItemModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class SearchRepository @Inject constructor(private val service: SearchService) {
    fun getSearchResultStream(query: String): Flow<PagingData<SearchItemModel>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { SearchPagingSource(service, query) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}