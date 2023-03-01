package com.example.mornhouse.mainact

import android.app.Application
import androidx.room.Room
import com.example.mornhouse.retrofitmodel.ApiRandomPoint
import com.example.mornhouse.retrofitmodel.PointRetrofitModule
import com.example.mornhouse.room.AppDataBase


class App : Application() {
    companion object {
        lateinit var api_random_point: ApiRandomPoint
        var database: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(applicationContext, AppDataBase::class.java, "DataBase.db")
            .fallbackToDestructiveMigration()
            .build()



        api_random_point = PointRetrofitModule().pointCreateApiInfo()!!

    }
}