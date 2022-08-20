package com.example.emoney.ui.fragment.emoney_update

import com.examples.core.ui.fragment.BaseUiHelper
import javax.inject.Inject

/**
 * Created by Shehab ELsarky.
 */
class CurrencyConverterUiHelper @Inject constructor() : BaseUiHelper() {

    internal fun calculateCurrencyRate(text: String, rate: String): String {
        return if (text.isNotEmpty() and rate.isNotEmpty())
            (text.toDouble() * rate.toDouble()).toString()
        else "0.0"
    }
}