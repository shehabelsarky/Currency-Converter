package com.example.emoney.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.emoney.domain.entity.latest.local.LatestCurrencyRate
import com.examples.core.ui.view_model.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Shehab Elsarky.
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class CurrencyConverterViewModel @Inject constructor() : BaseViewModel() {

    private val selectedCurrenciesList = arrayListOf<LatestCurrencyRate>()
    private val latestCurrencyRateMutableLiveData = MutableLiveData<List<LatestCurrencyRate>>()
    val latestCurrencyRateLiveData: LiveData<List<LatestCurrencyRate>> = latestCurrencyRateMutableLiveData

    fun addSelectedCurrency(currencyRate: LatestCurrencyRate){
        selectedCurrenciesList.add(0,currencyRate)
        latestCurrencyRateMutableLiveData.value = selectedCurrenciesList
    }
}