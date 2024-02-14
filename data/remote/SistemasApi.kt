package com.ucne.sistemas.data.remote

import com.ucne.sistemas.data.remote.dto.SistemasDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface SistemasApi {
    @GET("api/Sistemas")
    @Headers("X-API-Key: test")
    suspend fun getSistemas():List<SistemasDto>

    @GET("api/Sistemas/{id}")
    @Headers("X-API-Key: test")
    suspend fun getSistemasById(id: Int): SistemasDto

    @POST("api/Sistemas")
    @Headers("X-API-Key: test")
    suspend fun addSistemas(@Body sistemas: SistemasDto): Response<SistemasDto>
}