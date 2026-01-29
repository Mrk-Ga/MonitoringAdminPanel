package com.example.iot_project.models

import java.math.BigInteger

data class Client(
    val id: Int,
    val rfid: BigInteger,
    val name: String,
    val lastname: String,
    val email: String,
    val indeks: String
)


