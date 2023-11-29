package com.example.testtaskaeon.data.api

import com.example.testtaskaeon.data.model.PaymentResponse
import com.example.testtaskaeon.data.model.UserAuthRequest
import com.example.testtaskaeon.data.model.UserAuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("/api-test/login")
    suspend fun getAuthToken(@Body request: UserAuthRequest): UserAuthResponse

    @GET("/api-test/payments")
    suspend fun getPayments(@Header("token") token: String): PaymentResponse
}