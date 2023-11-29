package com.example.testtaskaeon.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskaeon.data.model.UserAuthRequest
import com.example.testtaskaeon.data.repository.AuthResult
import com.example.testtaskaeon.data.repository.AuthUserRepositoryImpl
import com.example.testtaskaeon.domain.AuthenticateUserUseCase
import kotlinx.coroutines.launch

class AuthUserViewModel : ViewModel() {

    private val authUserRepository = AuthUserRepositoryImpl()
    private val authenticateUserUseCase = AuthenticateUserUseCase(authUserRepository)

    private val _userAuthResult = MutableLiveData<AuthResult>()
    val authResult: LiveData<AuthResult> get() = _userAuthResult
    fun authenticateUser(userAuthRequest: UserAuthRequest) {
        viewModelScope.launch {
            _userAuthResult.value = authenticateUserUseCase.invoke(userAuthRequest)
        }
    }

}