package com.example.emoney.ui.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.emoney.domain.entity.latest.local.LatestCurrencyRate
import com.example.emoney.domain.entity.latest.query.LatestCurrencyQuery
import com.example.emoney.domain.usecase.LatestCurrencyRateUseCase
import com.examples.core.ui.view_model.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Shehab Elsarky.
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class EMoneyListViewModel @Inject constructor(
    private val latestCurrencyRateUseCase: LatestCurrencyRateUseCase
) : BaseViewModel() {

    private val latestCurrencyRateMutableLiveData = MutableLiveData<List<LatestCurrencyRate>>()
    val latestCurrencyRateLiveData: LiveData<List<LatestCurrencyRate>> = latestCurrencyRateMutableLiveData

    fun getLatestCurrenciesRate(query: LatestCurrencyQuery) {
        callApi(latestCurrencyRateMutableLiveData) {
            latestCurrencyRateUseCase.execute(query,it)
        }
    }
}