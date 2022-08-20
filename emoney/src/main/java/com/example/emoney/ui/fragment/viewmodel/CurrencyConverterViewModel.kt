package com.example.emoney.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.emoney.domain.entity.latest.local.LatestCurrencyRate
import com.example.emoney.domain.usecase.SelectedCurrencyRateUseCase
import com.examples.core.ui.view_model.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Shehab Elsarky.
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val selectedCurrencyRateUseCase: SelectedCurrencyRateUseCase
) : BaseViewModel() {

    private var selectedCurrenciesList = mutableListOf<LatestCurrencyRate>()
    private val latestCurrencyRateMutableLiveData = MutableLiveData<List<LatestCurrencyRate>>()
    val latestCurrencyRateLiveData: LiveData<List<LatestCurrencyRate>> =
        latestCurrencyRateMutableLiveData

    fun addSelectedCurrency(currencyRate: LatestCurrencyRate) {
        selectedCurrencyRateUseCase.setSelectedCurrencyList(
            selectedCurrenciesList,
            currencyRate
        )
        latestCurrencyRateMutableLiveData.value = selectedCurrenciesList
    }

    fun getSelectedCurrencyList(): MutableList<LatestCurrencyRate> = selectedCurrenciesList

}