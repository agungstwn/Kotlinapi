package com.agung.android.kotlinapi.utils.rxBus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.*

/**
 * Created by agung on 08/02/18.
 */
class RxBus {
    val bus: PublishSubject<Any> = PublishSubject.create<Any>()

    fun post(any: Any) = bus.onNext(any)

    fun observable(): Observable<Any> = bus
}