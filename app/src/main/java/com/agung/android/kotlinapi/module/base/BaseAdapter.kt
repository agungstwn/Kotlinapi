package com.agung.android.kotlinapi.module.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by agung on 05/02/18.
 */
abstract class BaseAdapter<D, VH : BaseViewHolder<D>> : RecyclerView.Adapter<VH>(){
    var items:MutableList<D> = mutableListOf()

    override fun getItemCount(): Int = items.size

    abstract fun getItemViewId(): Int

    abstract fun getViewHOlder(view: View?): VH

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(getItemViewId(),viewGroup, false)
        return getViewHOlder(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }

    fun getItem(position: Int) = items[position]
}