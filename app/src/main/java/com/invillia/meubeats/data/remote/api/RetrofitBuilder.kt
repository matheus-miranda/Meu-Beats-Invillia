package com.invillia.meubeats.data.remote.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "http://20.195.184.239/"

    private fun client(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor {
            HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun factory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()

        return MoshiConverterFactory.create(moshi)
    }

    fun createService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(factory())
            .client(client())
            .build()
    }
}