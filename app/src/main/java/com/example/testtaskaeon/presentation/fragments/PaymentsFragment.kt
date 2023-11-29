package com.example.testtaskaeon.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.testtaskaeon.R
import com.example.testtaskaeon.data.repository.PaymentsResult
import com.example.testtaskaeon.databinding.FragmentPaymentsBinding
import com.example.testtaskaeon.presentation.PaymentsAdapter
import com.example.testtaskaeon.presentation.basefragment.BaseFragment
import com.example.testtaskaeon.presentation.viewModels.PaymentViewModel
import com.example.testtaskaeon.utils.Constants

class PaymentsFragment : BaseFragment<FragmentPaymentsBinding>(
    FragmentPaymentsBinding::inflate
) {

    private val paymentViewModel: PaymentViewModel by viewModels()
    private lateinit var adapter: PaymentsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paymentViewModel.getPayments(arguments?.getString(Constants.KEY,"").toString())

        adapter = PaymentsAdapter()

        binding.recyclerView.adapter = adapter

        paymentViewModel.authResult.observe(requireActivity()) { result ->
            when (result) {
                is PaymentsResult.Success -> {
                    hideError()
                    adapter.submitList(result.paymentResponse.response.toList())
                }

                is PaymentsResult.Error -> {
                    showError(result.errorMessage)
                }

                else -> {}
            }
        }

        binding.retryButton.setOnClickListener {
            goToLoginFragment()
        }

        binding.buttonLogout.setOnClickListener {
            goToLoginFragment()
        }

    }

    private fun hideError() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.buttonLogout.visibility = View.VISIBLE
        binding.errorMessage.visibility = View.GONE
        binding.retryButton.visibility = View.GONE
    }

    private fun showError(errorMessage: String) {
        binding.recyclerView.visibility = View.GONE
        binding.buttonLogout.visibility = View.GONE
        binding.errorMessage.visibility = View.VISIBLE
        binding.retryButton.visibility = View.VISIBLE
        binding.errorMessage.text = errorMessage
    }

    private fun goToLoginFragment() {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, LoginFragment())
            ?.commit()
    }

}