package com.example.emoney.domain.usecase

import com.example.emoney.domain.entity.latest.local.LatestCurrencyRate
import javax.inject.Inject

/**
 * Created by Shehab Elsarky.
 */
class SelectedCurrencyRateUseCase @Inject constructor() {

    fun setSelectedCurrencyList(
        currencyList: MutableList<LatestCurrencyRate>,
        currencyRate: LatestCurrencyRate
    ){
         if (currencyList.isNotEmpty()) {
            val duplicatedItem = currencyList.find { it.countryCode == currencyRate.countryCode }
            duplicatedItem?.let {
                currencyList.remove(it)
            }
            currencyList.add(0, currencyRate)

        } else {
            currencyList.add(0, currencyRate)
        }
    }
}