package com.caparepa.basemvvmboilerplate.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.caparepa.basemvvmboilerplate.data.db.entity.current.WeatherDescription
import com.caparepa.basemvvmboilerplate.utils.WEATHER_DESCRIPTION_ID

@Dao
interface WeatherDescriptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherLocation: WeatherDescription)

    @Query("select * from bit_weather_description where id = $WEATHER_DESCRIPTION_ID")
    fun getWeatherDescription() : LiveData<WeatherDescription>

}