package com.example.testtaskaeon.data.repository

import com.example.testtaskaeon.data.api.RetrofitDispatcher
import com.example.testtaskaeon.data.model.UserAuthRequest
import com.example.testtaskaeon.data.model.UserAuthResponse
import com.example.testtaskaeon.domain.AuthUserRepository

class AuthUserRepositoryImpl : AuthUserRepository {

    override suspend fun authenticateUser(userAuthRequest: UserAuthRequest): AuthResult {
        return try {
            val response = RetrofitDispatcher.apiService.getAuthToken(userAuthRequest)

            if (response.success == "true") {
                AuthResult.Success(response)
            } else {
                AuthResult.Error("Неправильный логин или пароль")
            }
        } catch (e: Exception) {
            AuthResult.Error("Exception occurred: ${e.message}")
        }
    }

}

sealed class AuthResult {
    data class Success(val userAuthResponse: UserAuthResponse) : AuthResult()
    data class Error(val errorMessage: String) : AuthResult()
}
