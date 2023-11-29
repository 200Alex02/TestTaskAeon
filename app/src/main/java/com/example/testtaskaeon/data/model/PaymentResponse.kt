package com.example.testtaskaeon.data.model

data class PaymentResponse(
    val success: String,
    val response: ArrayList<PaymentDataResponse>
)

data class PaymentDataResponse(
    var id: String = "-",
    var title: String = "-",
    var amount: String = "-",
    var created: String = "-"
)
