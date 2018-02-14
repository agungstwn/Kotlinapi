package com.agung.android.kotlinapi.data.repository

import com.agung.android.kotlinapi.data.model.RepoResponse
import com.agung.android.kotlinapi.data.model.SearchResponse
import com.agung.android.kotlinapi.utils.rxBus.RxBus
import com.agung.android.kotlinapi.utils.rxBus.RxEvent
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.Android
import java.util.concurrent.ScheduledFuture
import javax.inject.Inject

/**
 * Created by agung on 14/02/18.
 */
class RepositoryDataSource @Inject constructor(
        val localDataSource: LocalDataSource,
        val remoteDataSource: RemoteDataSource,
        val rxBus: RxBus
) : GitHubDataSource {
    private var forceUpdate: Boolean = false

    override fun getRepos(gitHubUser: String, page: Int): Observable<MutableList<RepoResponse>> {
        when (page){
            1 -> {//Loading first page
                val localData = localDataSource.getRepos(gitHubUser)
                        .subscribeOn(Schedulers.io())
                        .doOnNext {
                            if (it.isEmpty()) forceUpdate = true
                        }
                return if (forceUpdate){
                    val remoteData = remoteDataSource.getRepos(gitHubUser)
                            .subscribeOn(Schedulers.io())
                            .flatMap {
                                localDataSource.getFavoriteRepos(gitHubUser).blockingSingle().forEach { localRepo->
                                    if (localRepo.favorite){
                                        val neccessaryRemoteRepo = it.find { remoteRepo -> localRepo.id == remoteRepo.id }
                                        neccessaryRemoteRepo?.favorite = localRepo.favorite
                                    }
                                }
                                localDataSource.addRepos(it)
                                forceUpdate = false
                                return@flatMap localDataSource.getRepos(gitHubUser)
                            }
                            .doOnComplete{
                                rxBus.post(RxEvent.UpdateRepoList(gitHubUser))
                            }
                    Observable.concatArrayDelayError(
                            localData.observeOn(AndroidSchedulers.mainThread()),
                            remoteData.observeOn(AndroidSchedulers.mainThread()))
                }else{
                    localData.observeOn(AndroidSchedulers.mainThread())
                }
            }
            else -> return remoteDataSource.getRepos(gitHubUser, page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun addRepos(repoResponses: List<RepoResponse>): Completable {
        return localDataSource.addRepos(repoResponses)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getRepo(gitHubUser: String, repoName: String): Observable<RepoResponse> {
        val localData = localDataSource.getRepo(gitHubUser, repoName)
                .subscribeOn(Schedulers.io())
        val remoteData = remoteDataSource.getRepo(gitHubUser, repoName)
                .subscribeOn(Schedulers.io())
                .flatMap {
                    val localRepo = localDataSource.getRepo(gitHubUser, repoName).blockingSingle()
                    if (localRepo.favorite) it.favorite = localRepo.favorite
                    localDataSource.addRepo(it)
                    return@flatMap localDataSource.getRepo(gitHubUser, repoName)
                }

        return Observable.concatArrayDelayError(
                localData.observeOn(AndroidSchedulers.mainThread()),
                remoteData.observeOn(AndroidSchedulers.mainThread())
        )
    }

    override fun addRepo(repoResponse: RepoResponse): Completable {
        return localDataSource.addRepo(repoResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteRepo(repoResponse: RepoResponse): Completable {
        return localDataSource.deleteRepo(repoResponse)
                .doOnComplete{
                    rxBus.post(RxEvent.DeleteRepo(repoResponse))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateRepo(repoResponse: RepoResponse): Completable {
        return localDataSource.updateRepo(repoResponse)
                .doOnComplete{
                    rxBus.post(RxEvent.UpdateRepo(repoResponse))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addRepoToFavorite(repoResponse: RepoResponse): Completable {
        return localDataSource.addRepoToFavorite(repoResponse)
                .doOnComplete{
                    rxBus.post(RxEvent.FavoriteRepo(repoResponse))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteRepoFromFavorite(repoResponse: RepoResponse): Completable {
        return localDataSource.deleteRepoFromFavorite(repoResponse)
                .doOnComplete{
                    rxBus.post(RxEvent.FavoriteRepo(repoResponse))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFavoriteRepos(gitHubUser: String): Observable<MutableList<RepoResponse>> {
        return localDataSource.getFavoriteRepos(gitHubUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun searchRepos(query: String, page: Int): Observable<SearchResponse> {
        return remoteDataSource.searchRepos(query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun refreshRepos() {
        forceUpdate = true
    }
}