package com.example.mornhouse.fragments.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mornhouse.mainact.App
import com.example.mornhouse.room.Point
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response


class PointVW : ViewModel() {


    private val _pointtMutableEmplLiveData = MutableLiveData<Point>()
    var pointtMutableEmplLiveData: LiveData<Point> =
        _pointtMutableEmplLiveData
    private lateinit var point:String

    fun getPoint(pointEdit: String,isRandom:Boolean) {
        val pointScope = CoroutineScope(Dispatchers.IO)
        pointScope.launch {
            point = if (isRandom){
                (0 until 10000).random().toString()
            } else{
                pointEdit
            }
            val pointtMain = App.api_random_point.getFact(point)
            pointtMain.enqueue(
                object : retrofit2.Callback<String> {
                    override fun onResponse(
                        call: Call<String>,
                        response: Response<String>,
                    ) {

                        val pointBody = response.body()

                        _pointtMutableEmplLiveData.value = Point(point.toInt(),pointBody.toString())

                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        //todo server error implementation
                    }

                }
            )
        }
    }
}