package com.example.testtaskaeon.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskaeon.R
import com.example.testtaskaeon.data.model.PaymentDataResponse
import com.example.testtaskaeon.databinding.ItemPaymentBinding

class PaymentsAdapter: ListAdapter<PaymentDataResponse, PaymentsAdapter.PaymentsHolder>(DiffCallBack()) {

    inner class PaymentsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemPaymentBinding.bind(itemView)

        fun bind(questionResult: PaymentDataResponse) = with(binding) {
            binding.realId.text = questionResult.id
            binding.realAmount.text = questionResult.amount
            binding.realTitle.text = questionResult.title
            binding.realCreated.text = questionResult.created
        }

    }

    companion object {
        private class DiffCallBack : DiffUtil.ItemCallback<PaymentDataResponse>() {
            override fun areItemsTheSame(
                oldItem: PaymentDataResponse,
                newItem: PaymentDataResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PaymentDataResponse,
                newItem: PaymentDataResponse
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentsHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_payment, parent, false)
        return PaymentsHolder(itemView)
    }

    override fun onBindViewHolder(holder: PaymentsHolder, position: Int) {
        val questionResult: PaymentDataResponse = getItem(position)
        holder.bind(questionResult)
    }
}