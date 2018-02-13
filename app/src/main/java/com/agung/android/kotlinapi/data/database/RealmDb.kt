package com.agung.android.kotlinapi.data.database

import android.content.Context
import com.agung.android.kotlinapi.data.model.RepoResponse
import io.realm.BuildConfig
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.log.LogLevel
import io.realm.log.RealmLog
import io.realm.rx.RealmObservableFactory

/**
 * Created by agung on 07/02/18.
 */
object RealmDb {

    private val REALM_DB_NAME = "kot.realm"

    private val SCHEMA_VERSION = 1

    @JvmStatic
    fun init(context: Context) {
        Realm.init(context)
        val config = RealmConfiguration.Builder()
                .name(REALM_DB_NAME)
                .schemaVersion(SCHEMA_VERSION.toLong())
                .deleteRealmIfMigrationNeeded()
                .rxFactory(RealmObservableFactory())
                .build()
        Realm.setDefaultConfiguration(config)

        if (BuildConfig.DEBUG) {
            RealmLog.setLevel(LogLevel.ERROR)
        }
    }

    @JvmStatic
    fun <T> insertOrUpdate(data: T) {
        when (data) {
            is RepoResponse -> Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction { r -> r.insertOrUpdate(data as RepoResponse) }
            }
            is List<*> -> Realm.getDefaultInstance().use { realm ->
                realm.executeTransaction { r ->
                    r.insertOrUpdate(
                            data.filterIsInstance<RepoResponse>())
                }
            }
        }
    }

    @JvmStatic
    fun delete(repoResponse: RepoResponse) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { r ->
                val result = r
                        .where(RepoResponse::class.java)
                        .equalTo(RepoResponse.REPO_ID, repoResponse.id)
                        .findAll()
                result.deleteAllFromRealm()
            }
        }
    }

    @JvmStatic
    fun getRepositories(): MutableList<RepoResponse> {
        var repoList: MutableList<RepoResponse> = mutableListOf()
        Realm.getDefaultInstance().use { realm ->
            val realmRepoList = realm
                    .where(RepoResponse::class.java)
                    .findAll()
            repoList = realm.copyFromRealm(realmRepoList)
            repoList.sortByDescending { repoResponse: RepoResponse? -> repoResponse?.id }
        }
        return repoList
    }

}