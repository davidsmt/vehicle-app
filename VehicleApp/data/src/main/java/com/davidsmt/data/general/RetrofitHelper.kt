package com.davidsmt.data.general

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by David SMT on 19/01/2019.
 */
class RetrofitHelper {

    private val retrofit: Retrofit.Builder

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        OkHttpClient().newBuilder().addNetworkInterceptor(
            HttpLoggingInterceptor().setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
        ).build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.build().create(service)
    }

    companion object {
        private const val BASE_URL = "http://private-6d86b9-vehicles5.apiary-mock.com"

        @Volatile
        private var instance: RetrofitHelper? = null

        fun getInstance() = instance ?: create()

        private fun create(): RetrofitHelper = synchronized(this) {
            instance ?: RetrofitHelper().also { instance = it }
        }
    }

}