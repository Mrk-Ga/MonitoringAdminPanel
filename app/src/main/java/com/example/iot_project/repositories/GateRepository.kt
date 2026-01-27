package com.example.iot_project.repositories

import com.example.iot_project.api.interfaces.GateApi
import com.example.iot_project.models.Gate

class GateRepository(
    private val api: GateApi
) {

    suspend fun getAll(): List<Gate> =
        api.getGates()

    suspend fun getById(id: Int): Gate =
        api.getGateById(id)

    suspend fun create(gate: Gate): Gate =
        api.createGate(gate)

    suspend fun update(gate: Gate): Gate =
        api.updateGate(gate.id, gate)

    suspend fun delete(id: Int) {
        api.deleteGate(id)
    }
}

