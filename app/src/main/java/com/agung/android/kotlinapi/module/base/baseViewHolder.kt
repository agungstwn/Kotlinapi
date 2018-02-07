package com.agung.android.kotlinapi.module.base

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by agung on 06/02/18.
 */
abstract class baseViewHolder<in D>(itemView : View?) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item : D)
}