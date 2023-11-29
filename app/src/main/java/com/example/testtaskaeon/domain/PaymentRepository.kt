package com.example.testtaskaeon.domain

import com.example.testtaskaeon.data.repository.PaymentsResult

interface PaymentRepository {

    suspend fun getPayments(token: String): PaymentsResult

}