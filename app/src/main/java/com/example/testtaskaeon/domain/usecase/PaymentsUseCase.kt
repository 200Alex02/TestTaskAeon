package com.example.testtaskaeon.domain.usecase

import com.example.testtaskaeon.data.repository.PaymentsResult
import com.example.testtaskaeon.domain.repository.PaymentRepository

class PaymentsUseCase(
    private val paymentRepository: PaymentRepository
) {
    suspend fun invoke(token: String): PaymentsResult {
        return paymentRepository.getPayments(token)
    }
}