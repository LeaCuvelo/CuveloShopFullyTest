package com.cuvelo.shopfully.test.ui.utils

import android.content.Context
import android.net.ConnectivityManager

fun isOnline(context: Context?): Boolean {
    val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null
}