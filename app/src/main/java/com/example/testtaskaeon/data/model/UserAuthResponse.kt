package com.example.testtaskaeon.data.model

data class UserAuthResponse(
    val success: String,
    val response: ResponseData
)

data class ResponseData(
    var token: String = ""
)