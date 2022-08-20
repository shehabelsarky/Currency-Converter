package com.example.emoney.ui.fragment.emoney_list

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.example.emoney.R
import com.example.emoney.databinding.FragmentEMoneyBinding
import com.example.emoney.domain.entity.latest.query.LatestCurrencyQuery
import com.example.emoney.ui.fragment.viewmodel.CurrencyConverterViewModel
import com.example.emoney.ui.fragment.viewmodel.EMoneyListViewModel
import com.examples.core.ui.fragment.BaseFragment
import com.examples.core.ui.fragment.BaseUiHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Shehab Elsarky.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class EMoneyListFragment : BaseFragment<FragmentEMoneyBinding, EMoneyListViewModel, BaseUiHelper>(
    FragmentEMoneyBinding::inflate
) {

    private val sharedViewModel: CurrencyConverterViewModel by navGraphViewModels(R.id.emoney_nav_graph) { defaultViewModelProviderFactory }
    override lateinit var viewModel: EMoneyListViewModel
    override lateinit var fragmentHelper: BaseUiHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLatestCurrenciesRate()
    }

    private fun observeLatestCurrenciesRate() {
        with(viewModel) {
            getLatestCurrenciesRate(
                LatestCurrencyQuery(
                    base = "EUR",
                    symbols = "GBP,JPY,USD,EGP,SAR"
                )
            )

            latestCurrencyRateLiveData.observe(viewLifecycleOwner) {

            }
        }
    }
}