package com.example.mornhouse.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Point::class], version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun pointDAO(): PointDAO

}
