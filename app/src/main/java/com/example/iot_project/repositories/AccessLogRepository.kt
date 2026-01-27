package com.example.iot_project.repositories

import com.example.iot_project.api.interfaces.AccessLogApi
import com.example.iot_project.models.AccessLog

class AccessLogRepository(
    private val api: AccessLogApi
) {

    suspend fun getAll(): List<AccessLog> =
        api.getAccessLogs()

    suspend fun getById(id: Int): AccessLog =
        api.getAccessLogById(id)

}