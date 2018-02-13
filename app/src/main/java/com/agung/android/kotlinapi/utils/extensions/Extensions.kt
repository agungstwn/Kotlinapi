package com.agung.android.kotlinapi.utils.extensions

import android.support.design.widget.Snackbar
import android.view.View
import com.agung.android.kotlinapi.data.model.RepoResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by agung on 08/02/18.
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable){
    compositeDisposable.addAll(this)
}

fun View.snack(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun emptyString(): String{
    return ""
}

fun emptyRepo(): RepoResponse{
    return RepoResponse()
}