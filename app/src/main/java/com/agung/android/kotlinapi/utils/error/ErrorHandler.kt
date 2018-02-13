package com.agung.android.kotlinapi.utils.error

import android.content.Context
import android.location.OnNmeaMessageListener
import com.agung.android.kotlinapi.R
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * Created by agung on 12/02/18.
 */
class ErrorHandler @Inject constructor(
        val context: Context
) {
    fun readError(error: Throwable, messageListener: (String) -> Unit){
        Timber.e(error)

        messageListener(when (error){
            is IOException -> context.getString(R.string.network_error)
            is HttpException -> when (error.code()){
                304 -> context.getString(R.string.not_modified_error)
                400 -> context.getString(R.string.bad_request_error)
                401 -> context.getString(R.string.unauthorized_error)
                403 -> context.getString(R.string.forbidden_error)
                404 -> context.getString(R.string.not_found_error)
                405 -> context.getString(R.string.method_not_allowed_error)
                409 -> context.getString(R.string.conflict_error)
                422 -> context.getString(R.string.unprocessable_error)
                500 -> context.getString(R.string.server_error_error)
                else -> context.getString(R.string.unknown_error)
            }
            is ObjectNotFoundException -> error.message
            else -> context.getString(R.string.unknown_error)
        })
    }
}