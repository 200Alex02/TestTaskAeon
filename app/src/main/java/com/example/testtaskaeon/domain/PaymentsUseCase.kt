package com.example.testtaskaeon.domain

import com.example.testtaskaeon.data.repository.PaymentsResult

class PaymentsUseCase(
    private val paymentRepository: PaymentRepository
) {
    suspend fun invoke(token: String): PaymentsResult {
        return paymentRepository.getPayments(token)
    }
}