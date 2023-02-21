package com.searchuser.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.searchuser.data.RepoRepository
import com.searchuser.data.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: RepoRepository
): ViewModel() {

    private var username: String? = savedStateHandle["username"]

    val repos =
        repository.getUserRepoStream(username ?: "").cachedIn(viewModelScope)
}