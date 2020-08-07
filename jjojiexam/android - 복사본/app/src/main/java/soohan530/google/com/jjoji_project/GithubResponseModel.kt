package soohan530.google.com.jjoji_project

import com.google.gson.annotations.SerializedName

class GithubResponseModel {
    @SerializedName("total_count")
    val totalCount: Int =  0

    @SerializedName("items")
    val items: List<GithubRepoModel> = listOf()
}