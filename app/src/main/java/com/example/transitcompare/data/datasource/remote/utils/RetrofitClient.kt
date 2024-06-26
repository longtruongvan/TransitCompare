package com.example.transitcompare.data.datasource.remote.utils

import android.content.Context
import com.example.transitcompare.data.datasource.remote.apiservice.TicketApiService
import com.example.transitcompare.data.datasource.remote.interceptor.CustomInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        private const val TIMEOUT_SECONDS = 60L

        private fun getClient(): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .baseUrl("https://run.mocky.io").build()
        }

        private fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder().apply {
                retryOnConnectionFailure(true)
                readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                addInterceptor(CustomInterceptor())
            }.build()
        }

        fun getTicketApiService(): TicketApiService {
            return getClient().create(TicketApiService::class.java)
        }

    }
}