package com.agung.android.kotlinapi.utils.rxBus

import com.agung.android.kotlinapi.data.model.RepoResponse

/**
 * Created by agung on 08/02/18.
 */
sealed class RxEvent {
    class UpdateRepoList(val gitHubUser: String) : RxEvent()
    class DeleteRepo(val repo: RepoResponse) : RxEvent()
    class UpdateRepo(val repo: RepoResponse) : RxEvent()
    class FavoriteRepo(val repo: RepoResponse) : RxEvent()
}