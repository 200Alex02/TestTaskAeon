package com.example.testtaskaeon.domain

import com.example.testtaskaeon.data.model.UserAuthRequest
import com.example.testtaskaeon.data.repository.AuthResult

interface AuthUserRepository {

    suspend fun authenticateUser(userAuthRequest: UserAuthRequest): AuthResult
}