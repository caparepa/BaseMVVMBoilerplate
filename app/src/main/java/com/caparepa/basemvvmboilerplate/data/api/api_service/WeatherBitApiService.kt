package com.caparepa.basemvvmboilerplate.data.api.api_service

import com.caparepa.basemvvmboilerplate.data.api.model.response.current.CurrentWeatherResponse
import com.caparepa.basemvvmboilerplate.data.api.model.response.forecast.ForecastWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherBitApiService {

    @GET("current")
    fun getCurrentWeather(
        @Query("city") city: String,
        @Query("lang") language: String = "en",
        @Query("units") units: String = "M"
    ): Response<CurrentWeatherResponse>

    @GET("current")
    fun getCurrentWeatherByLatLon(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("lang") language: String = "en",
        @Query("units") units: String = "M"
    ): Response<CurrentWeatherResponse>

    @GET("forecast/daily")
    fun getForecastWeather(
        @Query("city") city: String,
        @Query("lang") language: String = "en",
        @Query("units") units: String = "M"
    ): Response<ForecastWeatherResponse>

    @GET("forecast/daily")
    fun getForecastWeatherByLatLong(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("lang") language: String = "en",
        @Query("units") units: String = "M"
    ): Response<ForecastWeatherResponse>
}