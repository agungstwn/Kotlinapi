package com.agung.android.kotlinapi.module.adapter

import android.view.View
import com.agung.android.kotlinapi.R
import com.agung.android.kotlinapi.data.model.RepoResponse
import com.agung.android.kotlinapi.module.base.BaseAdapter
import com.agung.android.kotlinapi.module.base.BaseViewHolder
import com.agung.android.kotlinapi.utils.extensions.setTextOrHide
import com.agung.android.kotlinapi.utils.extensions.setTextWithFormatArgs
import com.agung.android.kotlinapi.utils.extensions.visible
import kotlinx.android.synthetic.main.item_repository.view.*


/**
 * Created by agung on 09/02/18.
 */
class RepoAdapter(
        val onItemClickListener: OnItemClickListener
) : BaseAdapter<RepoResponse, RepoAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int, repoResponse: RepoResponse)
        fun onItemLongClick(position: Int, repoResponse: RepoResponse)
    }

    class ViewHolder(
            itemView: View?,
            var onItemClickListener: OnItemClickListener
    ) : BaseViewHolder<RepoResponse>(itemView) {
        override fun onBind(item: RepoResponse) = with(itemView) {
            tv_repo_title.setTextOrHide(item.name)
            iv_repo_fav.visible(item.favorite)
            tv_repo_updated_at.setTextWithFormatArgs(R.string.updated_at, item.updatedAt?)

        }
    }
}

