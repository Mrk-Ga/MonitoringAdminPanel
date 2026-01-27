package com.example.iot_project.repositories

import com.example.iot_project.api.interfaces.BuildingApi
import com.example.iot_project.models.Building

class BuildingRepository(
    private val api: BuildingApi
) {

    suspend fun getAll(): List<Building> =
        api.getBuildings()

    suspend fun getById(id: Int): Building =
        api.getBuildingById(id)

    suspend fun create(building: Building): Building =
        api.createBuilding(building)

    suspend fun update(building: Building): Building =
        api.updateBuilding(building.id, building)

    suspend fun delete(id: Int) {
        api.deleteBuilding(id)
    }
}