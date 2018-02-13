package com.agung.android.kotlinapi.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by agung on 07/02/18.
 */
open class OwnerResponse(

        @PrimaryKey
        @SerializedName("id")
        val id: Long? = null,

        @SerializedName("login")
        val login: String? = null
) : RealmObject() {
    companion object {
        val OWNER_ID: String = "id"
        val OWNER_LOGIN: String = "login"
    }
}