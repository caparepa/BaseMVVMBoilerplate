package com.caparepa.basemvvmboilerplate.data.api.api_service

import com.caparepa.basemvvmboilerplate.BuildConfig
import com.caparepa.basemvvmboilerplate.data.api.interceptor.ConnectivityInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    fun makeApiService(connectivityInterceptor: ConnectivityInterceptor): WeatherBitApiService {
        //TODO: since every single request needs to send the "key" key for auth,
        //TODO: we create an Interceptor for injecting said value to the request
        val requestInterceptor = Interceptor { chain ->
            //Inject the access_key value to the request url
            val mUrl = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("key", BuildConfig.WEATHERBIT_API_KEY)
                .build()

            //Build the new url with the previous vlue injection
            val request = chain.request()
                .newBuilder()
                .url(mUrl)
                .build()

            //Proceed to intercept the current request and inject it with the modified url
            return@Interceptor chain.proceed(request)
        }

        //Since we need to intercept every single request made from this api service, we add
        //the interceptor to the HTTP client
        //TODO: We also add an interceptor to check the current network state using DI (WIP)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(connectivityInterceptor)
            .build()

        //Finally, we create the retrofit client with the previous interceptor,
        //a adapter factory and a converter factory, associated to the current interface
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.WEATHERBIT_BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherBitApiService::class.java)
    }

}