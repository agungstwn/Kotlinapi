package com.agung.android.kotlinapi.data.api

import com.agung.android.kotlinapi.data.model.RepoResponse
import com.agung.android.kotlinapi.data.model.SearchResponse
import com.agung.android.kotlinapi.utils.Constant.FIRST_PAGE
import com.agung.android.kotlinapi.utils.Constant.ITEMS_PER_PAGE
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by agung on 07/02/18.
 */
interface ApiService {
    @GET("users/{username}/repos")
    fun getRepos(
            @Path("username") username: String,
            @Query("client_id") client_id: String = "5301f7f2e5e82214454b",
            @Query("page") page: Int = FIRST_PAGE,
            @Query("per_page") per_page: Int = ITEMS_PER_PAGE,
            @Query("client_secret") client_secret: String = "00b3dc2b7c8f9641d71c569a961acbbddf497f8d"
    ): Observable<MutableList<RepoResponse>>

    @GET("repos/{username}/{repo_name}")
    fun getRepo(
            @Path("username") username: String,
            @Path("repo_name") repoName: String,
            @Query("client_id") client_id: String = "5301f7f2e5e82214454b",
            @Query("client_secret") client_secret: String = "00b3dc2b7c8f9641d71c569a961acbbddf497f8d"
    ): Observable<RepoResponse>

    @GET("search/repositories")
    fun searchRepos(
            @Query("client_id") client_id: String = "5301f7f2e5e82214454b",
            @Query("client_secret") client_secret: String = "00b3dc2b7c8f9641d71c569a961acbbddf497f8d",
            @Query("q") query: String,
            @Query("page") page: Int = FIRST_PAGE,
            @Query("per_page") per_page: Int = ITEMS_PER_PAGE
    ): Observable<SearchResponse>
}