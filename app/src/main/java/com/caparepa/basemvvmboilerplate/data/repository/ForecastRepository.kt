package com.caparepa.basemvvmboilerplate.data.repository

import androidx.lifecycle.LiveData
import com.caparepa.basemvvmboilerplate.data.db.entity.current.CurrentWeatherData
import com.caparepa.basemvvmboilerplate.data.db.entity.current.WeatherDescription
import com.caparepa.basemvvmboilerplate.data.db.entity.forecast.ForecastWeatherData
import com.caparepa.basemvvmboilerplate.data.db.entity.forecast.ForecastWeatherLocationData
import org.threeten.bp.LocalDate

interface ForecastRepository {

    //TODO: CHANGE RETURN TYPE TO REGULAR RESPONSE (!?)
    suspend fun getCurrentWeatherData() : LiveData<out CurrentWeatherData>

    suspend fun getFutureWeatherList(startDate: LocalDate): LiveData<out List<ForecastWeatherData>>

    suspend fun getFutureWeatherByDate(date: LocalDate) : LiveData<out ForecastWeatherData>

    suspend fun getWeatherDescription() : LiveData<WeatherDescription>

    suspend fun getForecastLocation() : LiveData<ForecastWeatherLocationData>

}