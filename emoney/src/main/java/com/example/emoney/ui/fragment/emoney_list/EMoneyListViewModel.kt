package com.example.emoney.ui.fragment.emoney_list

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


}