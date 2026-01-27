package com.example.iot_project.api.interfaces

import com.example.iot_project.models.AccessLog
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AccessLogApi {

    @GET("api/logs")
    suspend fun getAccessLogs(): List<AccessLog>

    @GET("api/logs/{id}")
    suspend fun getAccessLogById(
        @Path("id") id: Int
    ): AccessLog

/*    @POST("api/logs")
    suspend fun createAccessLog(
        @Body accessLog: AccessLog
    ): AccessLog

    @PUT("api/logs/{id}")
    suspend fun updateAccessLog(
        @Path("id") id: Int,
        @Body accessLog: AccessLog
    ): AccessLog

    @DELETE("api/logs/{id}")
    suspend fun deleteAccessLog(
        @Path("id") id: Int
    ): Response<Unit>*/
}