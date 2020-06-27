package com.caparepa.basemvvmboilerplate.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.caparepa.basemvvmboilerplate.data.db.entity.forecast.ForecastWeatherData
import com.caparepa.basemvvmboilerplate.data.db.entity.forecast.ForecastWeatherLocationData
import com.caparepa.basemvvmboilerplate.utils.FUTURE_WEATHER_LOCATION_DATA_ID
import org.threeten.bp.LocalDate

@Dao
interface FutureWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecastWeatherEntries: List<ForecastWeatherData>)

    @Query("select * from future_weather where date(bitDatetime) >= date(:startDate)")
    fun getFutureWeatherDetail(startDate: LocalDate): LiveData<List<ForecastWeatherData>>

    @Query("select count(id) from future_weather where date(bitDatetime) >= date(:startDate)")
    fun countFutureWeather(startDate: LocalDate) : Int

    @Query("delete from future_weather where date(bitDatetime) < date(:firstDateToKeep)")
    fun deleteOldEntries(firstDateToKeep: LocalDate)

    //Get future weather detail by date
    @Query("select * from future_weather where date(bitDateTime) = date(:date)")
    fun getDetailWeatherByDate(date: LocalDate) : LiveData<ForecastWeatherData>

    //For the location
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertLocationData(forecastLocation: ForecastWeatherLocationData)

    @Query("select * from future_weather_location_data where id = $FUTURE_WEATHER_LOCATION_DATA_ID")
    fun getFutureWeatherLocationData(): LiveData<ForecastWeatherLocationData>
}