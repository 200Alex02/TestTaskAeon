package com.example.testtaskaeon.domain.usecase

import com.example.testtaskaeon.data.model.UserAuthRequest
import com.example.testtaskaeon.data.repository.AuthResult
import com.example.testtaskaeon.domain.repository.AuthUserRepository

class AuthenticateUserUseCase(
    private val authUserRepository: AuthUserRepository
) {

    suspend operator fun invoke (userAuthRequest: UserAuthRequest): AuthResult {
        return authUserRepository.authenticateUser(userAuthRequest)
    }
}