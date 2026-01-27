package com.example.iot_project.repositories

import com.example.iot_project.api.interfaces.ClientApi
import com.example.iot_project.models.Client

class ClientRepository(
    private val api: ClientApi
) {

    suspend fun getAll(): List<Client> =
        api.getClients()

    suspend fun getById(id: Int): Client =
        api.getClientById(id)

    suspend fun create(client: Client): Client =
        api.createClient(client)

    suspend fun update(client: Client): Client =
        api.updateClient(client.id, client)

    suspend fun delete(id: Int) {
        api.deleteClient(id)
    }
}