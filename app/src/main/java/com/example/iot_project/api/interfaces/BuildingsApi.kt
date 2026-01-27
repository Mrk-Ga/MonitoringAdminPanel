package com.example.iot_project.api.interfaces

import com.example.iot_project.models.Building
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface BuildingApi {

    @GET("api/buildings")
    suspend fun getBuildings(): List<Building>

    @GET("api/buildings/{id}")
    suspend fun getBuildingById(
        @Path("id") id: Int
    ): Building

    @POST("api/buildings")
    suspend fun createBuilding(
        @Body building: Building
    ): Building

    @PUT("api/buildings/{id}")
    suspend fun updateBuilding(
        @Path("id") id: Int,
        @Body building: Building
    ): Building

    @DELETE("api/buildings/{id}")
    suspend fun deleteBuilding(
        @Path("id") id: Int
    ): Response<Unit>
}
