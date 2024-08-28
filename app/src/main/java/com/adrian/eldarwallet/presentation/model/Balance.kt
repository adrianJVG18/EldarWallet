package com.adrian.eldarwallet.presentation.model

import java.text.NumberFormat
import java.util.Locale


data class Balance(
    val amount: Double
) {
    fun getFormattedAmount(): String {
        return NumberFormat.getNumberInstance(Locale.US)
            .format(amount)
            .replace(",", "،")
    }
}