package com.searchuser.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.searchuser.data.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: SearchRepository
): ViewModel() {

    private var query: String? = savedStateHandle["query"]

    val users =
        repository.getSearchResultStream(query ?: "").cachedIn(viewModelScope)
}