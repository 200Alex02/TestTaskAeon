package com.example.testtaskaeon.domain

import com.example.testtaskaeon.data.model.UserAuthRequest
import com.example.testtaskaeon.data.repository.AuthResult

class AuthenticateUserUseCase(
    private val authUserRepository: AuthUserRepository
) {

    suspend operator fun invoke (userAuthRequest: UserAuthRequest): AuthResult {
        return authUserRepository.authenticateUser(userAuthRequest)
    }
}