package com.example.mornhouse.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mornhouse.room.Point

@Dao
interface PointDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPointHistory(point: Point)

    @Query("SELECT * FROM Point")
    fun loadPoint():List<Point>
}
