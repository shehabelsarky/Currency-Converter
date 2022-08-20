package com.example.emoney.domain.usecase

import com.example.emoney.domain.entity.latest.local.LatestCurrencyRate
import javax.inject.Inject

class SelectedCurrencyRateUseCase @Inject constructor() {

    fun setSelectedCurrencyList(currencyList: MutableList<LatestCurrencyRate>,currencyRate: LatestCurrencyRate){
        currencyList
            .filter { it.countryCode != currencyRate.countryCode }
            .toMutableList()
            .add(0, currencyRate)
    }
}