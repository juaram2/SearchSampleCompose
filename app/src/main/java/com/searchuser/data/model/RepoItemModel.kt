package com.searchuser.data.model

import com.google.gson.annotations.SerializedName

data class RepoItemModel(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("node_id") val nodeId: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("full_name") val fullName: String,
    @field:SerializedName("owner") val owner: SearchItemModel,
    @field:SerializedName("private") val private: Boolean,
    @field:SerializedName("html_url") val htmlUrl: String,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("fork") val fork: Boolean,
    @field:SerializedName("url") val url: String
)