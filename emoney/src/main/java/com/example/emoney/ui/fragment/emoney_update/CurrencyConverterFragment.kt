package com.example.emoney.ui.fragment.emoney_update

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.example.emoney.R
import com.example.emoney.databinding.FragmentCurrencyConverterBinding
import com.example.emoney.ui.fragment.viewmodel.CurrencyConverterViewModel
import com.examples.core.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

/**
 * Created by Shehab Elsarky.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CurrencyConverterFragment :
    BaseFragment<FragmentCurrencyConverterBinding, CurrencyConverterViewModel, CurrencyConverterUiHelper>(
        FragmentCurrencyConverterBinding::inflate) {

    override val viewModel: CurrencyConverterViewModel by navGraphViewModels(R.id.emoney_nav_graph) { defaultViewModelProviderFactory }

    @Inject
    override lateinit var fragmentHelper: CurrencyConverterUiHelper
    private lateinit var currencyUpdateListAdapter: CurrencyUpdateListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCurrencyList()
        observeLatestCurrenciesRate()
    }

    private fun setCurrencyList() {
        currencyUpdateListAdapter = CurrencyUpdateListAdapter()
        binding.rvCurrencyList.adapter = currencyUpdateListAdapter
    }

    private fun observeLatestCurrenciesRate() =
        with(viewModel) {
            latestCurrencyRateLiveData.observe(viewLifecycleOwner) {
                currencyUpdateListAdapter.submitList(it)
            }
        }
}