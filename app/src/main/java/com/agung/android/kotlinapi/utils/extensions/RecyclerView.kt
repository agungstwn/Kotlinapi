package com.agung.android.kotlinapi.utils.extensions

import android.support.v7.widget.RecyclerView
import timber.log.Timber

/**
 * Created by agung on 12/02/18.
 */
fun RecyclerView.onScrollToBottom(doAction: (Unit) -> Unit)
        = addOnScrollListener(object : RecyclerView.OnScrollListener(){
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (!recyclerView.canScrollVertically(-1) && (dy < 0)){
            //top
        }else if (!recyclerView.canScrollVertically(-1) && (dy > 0)){
            //bottom
            Timber.d("Reached recyclerView bottom")
            doAction(Unit)
        }

        if (dy > 0){
            //scroll to top
        }else if (dy < 0){
            //scroll to bottom
        }
    }
})

fun RecyclerView.ifNotScrollable(doAction: (Unit) -> Unit){
    if (this.computeVerticalScrollRange() <= this.height){
        doAction(Unit)
    }
}