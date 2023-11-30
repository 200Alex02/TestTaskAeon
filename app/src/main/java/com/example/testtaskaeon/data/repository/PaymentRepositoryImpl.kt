package com.example.testtaskaeon.data.repository

import com.example.testtaskaeon.data.api.RetrofitDispatcher
import com.example.testtaskaeon.data.model.PaymentResponse
import com.example.testtaskaeon.domain.repository.PaymentRepository

class PaymentRepositoryImpl: PaymentRepository {

    override suspend fun getPayments(token: String): PaymentsResult {
        return try {
            val response = RetrofitDispatcher.apiService.getPayments(token)

            if (response.success == "true") {
                PaymentsResult.Success(response)
            } else {
                PaymentsResult.Error("Не удалось получить список платежей")
            }
        } catch (e: Exception) {
            PaymentsResult.Error("Exception occurred: ${e.message}")
        }
    }
}

sealed class PaymentsResult {
    data class Success(val paymentResponse: PaymentResponse) : PaymentsResult()
    data class Error(val errorMessage: String) : PaymentsResult()
}