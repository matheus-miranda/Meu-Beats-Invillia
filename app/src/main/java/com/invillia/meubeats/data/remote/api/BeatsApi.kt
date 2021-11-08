package com.invillia.meubeats.data.remote.api

import com.invillia.meubeats.data.remote.dto.NetworkHeadphone
import retrofit2.http.GET

interface BeatsApi {
    @GET("beats")
    suspend fun getHeadphones(): List<NetworkHeadphone>
}