package com.agung.android.kotlinapi.data.repository

import android.graphics.pdf.PdfDocument
import com.agung.android.kotlinapi.data.model.RepoResponse
import com.agung.android.kotlinapi.data.model.SearchResponse
import com.agung.android.kotlinapi.module.adapter.RepoAdapter
import io.reactivex.Completable
import io.reactivex.Observable
import java.time.temporal.TemporalQuery
import java.util.*

/**
 * Created by agung on 14/02/18.
 */
interface GitHubDataSource {
    fun getRepos(gitHubUser: String, page: Int = 1): Observable<MutableList<RepoResponse>>

    fun addRepos(repoResponses: List<RepoResponse>): Completable

    fun getRepo(gitHubUser: String, repoName: String): Observable<RepoResponse>

    fun addRepo(repoResponse: RepoResponse): Completable

    fun deleteRepo(repoResponse: RepoResponse): Completable

    fun updateRepo(repoResponse: RepoResponse): Completable

    fun addRepoToFavorite(repoResponse: RepoResponse): Completable

    fun deleteRepoFromFavorite(repoResponse: RepoResponse): Completable

    fun getFavoriteRepos(gitHubUser: String): Observable<MutableList<RepoResponse>>

    fun searchRepos(query: String, page: Int): Observable<SearchResponse>

    fun refreshRepos()
}