package com.agung.android.kotlinapi.utils.extensions

import android.content.res.Resources
import com.agung.android.kotlinapi.R
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by agung on 09/02/18.
 */
fun String.formatDate(): String{
    val initialFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH)
    val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    var date = Date()

    try {
        date = initialFormat.parse(this)
    }catch (e:Exception){
        e.printStackTrace()
    }
    return format.format(date).toString()
}

fun Long.formatTimeMillisToISO(): String =
        SimpleDateFormat("yyyy-MM-dd'T'hh-mm-ss'Z'", Locale.getDefault())
                .format(this).toString()

fun Date.formatDate(resources: Resources):String{
    val localMillis = DateTimeZone.getDefault().convertUTCToLocal(this.time)
    val timeDelta = (System.currentTimeMillis() - localMillis)/1000L
    val timeStr = when{
        timeDelta < 60 -> resources.getString(R.string.time_sec, timeDelta)
        timeDelta < 60 * 60 -> resources.getString(R.string.time_min, timeDelta / 60)
        timeDelta < 60 * 60 * 24 -> resources.getString(R.string.time_hour, timeDelta / 60 * 60)
        timeDelta < 60 * 60 * 24 * 7 -> resources.getString(R.string.time_day, timeDelta / 60 * 60 * 24)
        else -> return DateTimeFormat.forPattern("dd-MM-yyyy").print(localMillis)
    }
    return resources.getString(R.string.time_ago, timeStr)
}