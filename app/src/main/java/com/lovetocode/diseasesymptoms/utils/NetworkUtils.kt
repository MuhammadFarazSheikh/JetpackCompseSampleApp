package com.montymobile.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtils
{
    /*CHECK IF INTERNET CONNECTED*/
    public fun checkIfInternetConnected(context: Context):Boolean
    {
        var connectivityManager=(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        var wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        var mobileData = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        return (wifi?.isConnected!! || mobileData?.isConnected!!)
    }
}