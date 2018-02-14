package com.agung.android.kotlinapi.data.repository

import com.agung.android.kotlinapi.data.api.ApiService
import com.agung.android.kotlinapi.data.model.RepoResponse
import com.agung.android.kotlinapi.data.model.SearchResponse
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by agung on 14/02/18.
 */
class RemoteDataSource @Inject constructor(
        val apiService: ApiService
) : GitHubDataSource {
    override fun getRepos(gitHubUser: String, page: Int): Observable<MutableList<RepoResponse>> {
        return apiService.getRepos(username = gitHubUser, page = page)
                .doOnNext {
                    Timber.i("Loaded ${it.size} repos")
                }
    }

    override fun getRepo(gitHubUser: String, repoName: String): Observable<RepoResponse> {
        return apiService.getRepo(gitHubUser, repoName)
                .doOnNext {
                    Timber.i("Loaded ${it.name}")
                }
    }

    override fun addRepos(repoResponses: List<RepoResponse>): Completable = Completable.complete()

    override fun addRepo(repoResponse: RepoResponse): Completable = Completable.complete()

    override fun deleteRepo(repoResponse: RepoResponse): Completable = Completable.complete()

    override fun updateRepo(repoResponse: RepoResponse): Completable = Completable.complete()

    override fun addRepoToFavorite(repoResponse: RepoResponse): Completable = Completable.complete()

    override fun deleteRepoFromFavorite(repoResponse: RepoResponse): Completable = Completable.complete()

    override fun getFavoriteRepos(gitHubUser: String): Observable<MutableList<RepoResponse>> = Observable.empty()

    override fun searchRepos(query: String, page: Int): Observable<SearchResponse> {
        return apiService.searchRepos(query = query, page = page)
                .doOnNext {
                    Timber.i("Search result: ${it.totalCount} repos")
                }
    }

    override fun refreshRepos() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}