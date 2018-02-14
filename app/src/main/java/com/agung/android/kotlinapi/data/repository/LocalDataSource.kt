package com.agung.android.kotlinapi.data.repository

import com.agung.android.kotlinapi.data.database.RealmDb
import com.agung.android.kotlinapi.data.model.RepoResponse
import com.agung.android.kotlinapi.data.model.SearchResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber

/**
 * Created by agung on 14/02/18.
 */
class LocalDataSource : GitHubDataSource {
    override fun getRepos(gitHubUser: String, page: Int): Observable<MutableList<RepoResponse>> {
        return Observable.just(RealmDb.getRepositories())
                .doOnNext { Timber.i("Retrieved ${it.size} repo") }
    }

    override fun addRepos(repoResponses: List<RepoResponse>): Completable {
        return Single.just(RealmDb.insertOrUpdate(repoResponses))
                .toCompletable()
                .doOnComplete(){
                    Timber.i("Added ${repoResponses.size} repo")
                }
    }

    override fun getRepo(gitHubUser: String, repoName: String): Observable<RepoResponse> {
        return Observable.just(RealmDb.getRepository(repoName))
                .doOnNext {
                    Timber.i("Retrieved ${it.name} repo")
                }
    }

    override fun addRepo(repoResponse: RepoResponse): Completable {
        return Single.just(RealmDb.insertOrUpdate(repoResponse))
                .toCompletable()
                .doOnComplete{
                    Timber.i("Added ${repoResponse.name} repo")
                }
    }

    override fun deleteRepo(repoResponse: RepoResponse): Completable {
        return Single.just(RealmDb.delete(repoResponse))
                .toCompletable()
                .doOnComplete{
                    Timber.i("Delete ${repoResponse.name} repo")
                }
    }

    override fun updateRepo(repoResponse: RepoResponse): Completable {
        return Single.just(RealmDb.insertOrUpdate(repoResponse))
                .toCompletable()
                .doOnComplete{
                    Timber.i("Update ${repoResponse.name} repo")
                }
    }

    override fun addRepoToFavorite(repoResponse: RepoResponse): Completable {
        return Single.just(RealmDb.insertOrUpdate(repoResponse))
                .toCompletable()
                .doOnComplete{
                    Timber.i("Added ${repoResponse.name} repo from favorites")
                }
    }

    override fun deleteRepoFromFavorite(repoResponse: RepoResponse): Completable {
        repoResponse.favorite = false
        return Single.just(RealmDb.insertOrUpdate(repoResponse))
                .toCompletable()
                .doOnComplete{
                    Timber.i("Delete ${repoResponse.name} repo from favorites")
                }
    }

    override fun getFavoriteRepos(gitHubUser: String): Observable<MutableList<RepoResponse>> {
        return Observable.just(RealmDb.getFavorites())
                .doOnNext {
                    Timber.i("Retrieved ${it.size} favorites repos")
                }
    }

    override fun searchRepos(query: String, page: Int): Observable<SearchResponse> {
        return Observable.empty()
    }

    override fun refreshRepos() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}