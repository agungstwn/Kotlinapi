package com.agung.android.kotlinapi.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.security.acl.Owner

/**
 * Created by agung on 07/02/18.
 */
open class RepoResponse(

        @PrimaryKey
        @SerializedName("id")
        var id: Long? = null,

        @SerializedName("name")
        var name: String? = null,

        @SerializedName("html_url")
        var htmlUrl: String? = null,

        @SerializedName("description")
        var description: String? = null,

        @SerializedName("language")
        var language: String? = null,

        @SerializedName("stargazersCount")
        var stargazersCount: String? = null,

        @SerializedName("updated_at")
        var updatedAt: String? = null,

        @SerializedName("network_count")
        var networkCount: String? = null,

        @SerializedName("subscribers_count")
        var subscribersCount: String? = null,

        @SerializedName("owner")
        var owner: Owner? = null,

        var favorite: Boolean = false
) : RealmObject() {

    companion object {
            val REPO_ID: String = "id"
            val REPO_NAME: String = "name"
            val REPO_IS_FAVORITE = "favorite"
    }
}