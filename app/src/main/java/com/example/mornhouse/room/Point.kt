package com.example.mornhouse.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Point (
    @PrimaryKey(autoGenerate = true)
    var idPoint:Int,
    var pointFacts:String,
        ):java.io.Serializable