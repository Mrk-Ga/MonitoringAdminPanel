package com.example.iot_project.api.interfaces

import com.example.iot_project.models.Gate
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GateApi {

    @GET("api/gates")
    suspend fun getGates(): List<Gate>

    @GET("api/gates/{id}")
    suspend fun getGateById(
        @Path("id") id: Int
    ): Gate

    @POST("api/gates")
    suspend fun createGate(
        @Body gate: Gate
    ): Gate

    @PUT("api/gates/{id}")
    suspend fun updateGate(
        @Path("id") id: Int,
        @Body gate: Gate
    ): Gate

    @DELETE("api/gates/{id}")
    suspend fun deleteGate(
        @Path("id") id: Int
    ): Response<Unit>
}