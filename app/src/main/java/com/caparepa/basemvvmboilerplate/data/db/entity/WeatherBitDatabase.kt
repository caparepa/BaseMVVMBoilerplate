package com.caparepa.basemvvmboilerplate.data.db.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.caparepa.basemvvmboilerplate.data.db.entity.current.CurrentWeatherData
import com.caparepa.basemvvmboilerplate.data.db.entity.current.WeatherDescription
import com.caparepa.basemvvmboilerplate.data.db.entity.forecast.ForecastWeatherData
import com.caparepa.basemvvmboilerplate.data.db.entity.forecast.ForecastWeatherLocationData
import com.caparepa.basemvvmboilerplate.utils.LocalDateConverter

@Database(
    entities = [
        CurrentWeatherData::class,
        WeatherDescription::class,
        ForecastWeatherData::class,
        ForecastWeatherLocationData::class
    ],
    version = 1
)
@TypeConverters(LocalDateConverter::class)
abstract class WeatherBitDatabase : RoomDatabase() {

    /*abstract fun getCurrentWeatherDataDao() : CurrentWeatherDataDao
    abstract fun getWeatherDescriptionDao() : WeatherDescriptionDao
    abstract fun getFutureWeatherDao() : FutureWeatherDao*/

    //We create a companion object that will act as singleton in order to create a single instance
    //of the database
    companion object {

        //@Volatile annotation allows all of the threads to have access to the property (instance)
        @Volatile
        private var instance: WeatherBitDatabase? = null

        //Lock object for the threads
        private val LOCK = Any()

        //We create an operator function to initialize the database
        //If there is an instance, return it (instance ?:)
        //else, syncronize a block with a lock, check again if there is an instance,

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                //if there is no instance, build a database (buildDatabase(context))
                //and also, whatever is returned from the builder, set the instance equal to "it"
                instance
                    ?: buildDatabase(
                        context
                    ).also {
                        instance = it
                    }
            }

        //We create a function to build the database, which will receive the application context
        ///This builder will initialize the database java class for the app (ForecastDatabase)
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WeatherBitDatabase::class.java, "bitweather.db"
            ).build()

    }

}