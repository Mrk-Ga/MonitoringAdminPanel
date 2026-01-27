package com.example.iot_project.api.interfaces

import com.example.iot_project.models.Client
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClientApi {

    @GET("api/users")
    suspend fun getClients(): List<Client>

    @GET("api/users/{id}")
    suspend fun getClientById(
        @Path("id") id: Int
    ): Client

    @POST("api/users")
    suspend fun createClient(
        @Body client: Client
    ): Client

    @PUT("api/users/{id}")
    suspend fun updateClient(
        @Path("id") id: Int,
        @Body client: Client
    ): Client

    @DELETE("api/users/{id}")
    suspend fun deleteClient(
        @Path("id") id: Int
    ): Response<Unit>
}