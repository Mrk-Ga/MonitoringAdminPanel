package com.example.iot_project.models

data class AccessLog(
    val id:Int,
    val operation: String,
    val user_id: Int,
    val gate_id: Int,
    val access_time: String
)