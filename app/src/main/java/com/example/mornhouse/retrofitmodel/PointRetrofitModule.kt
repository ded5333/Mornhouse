package com.example.mornhouse.retrofitmodel

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class PointRetrofitModule {

    private fun pointCreateClient(): OkHttpClient {
        val loading = HttpLoggingInterceptor()
        loading.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(
            object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request: Request.Builder = chain.request().newBuilder()
                    return chain.proceed(request.build())
                }

            }
        )
            .addInterceptor(loading)
        return okHttpClient.build()

    }

    fun pointCreateApiInfo(): ApiRandomPoint? {
        val pointRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Config.POINT_URL)
            .client(pointCreateClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        return pointRetrofit.create(ApiRandomPoint::class.java)


    }
}