package com.example.testtaskaeon.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskaeon.data.repository.PaymentRepositoryImpl
import com.example.testtaskaeon.data.repository.PaymentsResult
import com.example.testtaskaeon.domain.PaymentsUseCase
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel() {

    private val paymentRepository = PaymentRepositoryImpl()
    private val paymentsUseCase = PaymentsUseCase(paymentRepository)

    private val _paymentsResult= MutableLiveData<PaymentsResult?>()
    val authResult: LiveData<PaymentsResult?> get() = _paymentsResult

    fun getPayments(token: String) {
        viewModelScope.launch {
            _paymentsResult.value = paymentsUseCase.invoke(token)
        }
    }
}