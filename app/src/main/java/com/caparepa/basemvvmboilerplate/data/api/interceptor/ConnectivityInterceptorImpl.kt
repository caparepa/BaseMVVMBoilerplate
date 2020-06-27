package com.caparepa.basemvvmboilerplate.data.api.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.caparepa.basemvvmboilerplate.data.api.interceptor.ConnectivityInterceptor
import com.caparepa.basemvvmboilerplate.internal.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(context: Context) :
    ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw NoConnectivityException() //Custom exception created for this interceptor
        return chain.proceed(chain.request())
    }

    //Helper function to check from system services whether there is networkconnection
    private fun isOnline(): Boolean {
        //We create a connectivity manager from system services and cast it as ConnectivityManager
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo //We get network info
        return networkInfo != null && networkInfo.isConnected
    }
}