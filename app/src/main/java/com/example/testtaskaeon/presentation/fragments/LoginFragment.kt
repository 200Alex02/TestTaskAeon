package com.example.testtaskaeon.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.testtaskaeon.R
import com.example.testtaskaeon.data.model.UserAuthRequest
import com.example.testtaskaeon.data.repository.AuthResult
import com.example.testtaskaeon.databinding.FragmentLoginBinding
import com.example.testtaskaeon.presentation.basefragment.BaseFragment
import com.example.testtaskaeon.presentation.viewModels.AuthUserViewModel
import com.example.testtaskaeon.utils.Constants

class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    private val authUserViewModel: AuthUserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = Bundle()

        binding.loginbtn.setOnClickListener {
            val userName = binding.username.text.toString()
            val password = binding.password.text.toString()

            if (userName.isNotEmpty() && password.isNotEmpty()) {
                val userAuthRequest = UserAuthRequest(userName, password)
                authUserViewModel.authenticateUser(userAuthRequest)
            } else {
                Toast.makeText(requireContext(), "Введите логин и пароль", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        authUserViewModel.authResult.observe(requireActivity()) { result ->
            when (result) {
                is AuthResult.Success -> {
                    binding.errorMessage.visibility = View.GONE
                    val token = result.userAuthResponse.response.token
                    bundle.putString(Constants.KEY, token)
                    goToPaymentsFragment(bundle)
                }

                is AuthResult.Error -> {
                    showError(result.errorMessage)
                }
            }
        }
    }

    private fun showError(errorMessage: String) {
        binding.errorMessage.text = errorMessage
        binding.errorMessage.visibility = View.VISIBLE
    }

    private fun goToPaymentsFragment(bundle: Bundle) {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, PaymentsFragment().apply {
                arguments = bundle
            })
            ?.commit()
    }
}