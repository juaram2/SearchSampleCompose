package com.searchuser.api

import android.util.Log
import com.searchuser.BuildConfig
import com.searchuser.data.model.RepoItemModel
import com.searchuser.data.model.SearchModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface SearchService {
    @GET("search/users")
    suspend fun searchUser(
        @Query("q") query: String,
        @Query("page") page: Int
    ) : Response<SearchModel>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("sort") sort: String = "updated"
    ) : Response<List<RepoItemModel>>

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): SearchService {
            val logger = HttpLoggingInterceptor { message ->
                Log.d("debug", "okHttpClientBuilder: $message")
            }.apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }

            val client = OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/vnd.github+json")
                        .addHeader("Authorization", "Bearer YOUR_TOKEN")
                        .addHeader("X-GitHub-Api-Version", "2022-11-28")
                    chain.proceed(request.build())
                }
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SearchService::class.java)
        }
    }
}
