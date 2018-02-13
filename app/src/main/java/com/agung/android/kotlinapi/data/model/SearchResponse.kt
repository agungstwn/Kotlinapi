package com.agung.android.kotlinapi.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by agung on 07/02/18.
 */
open class SearchResponse(

        @SerializedName("total_count")
        val totalCount: Int,

        @SerializedName("incomplete_results")
        val incomplete: Boolean,

        @SerializedName("items")
        val items: MutableList<RepoResponse>
)