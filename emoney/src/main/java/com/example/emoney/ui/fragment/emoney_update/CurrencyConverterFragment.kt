package com.example.emoney.ui.fragment.emoney_update

import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.example.emoney.R
import com.example.emoney.databinding.FragmentCurrencyConverterBinding
import com.example.emoney.ui.fragment.viewmodel.CurrencyConverterViewModel
import com.examples.core.ui.fragment.BaseFragment
import com.examples.core.ui.fragment.BaseUiHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Shehab Elsarky.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CurrencyConverterFragment :
    BaseFragment<FragmentCurrencyConverterBinding, CurrencyConverterViewModel, BaseUiHelper>(
        FragmentCurrencyConverterBinding::inflate
    ) {

    override val viewModel: CurrencyConverterViewModel by navGraphViewModels(R.id.emoney_nav_graph) { defaultViewModelProviderFactory }
    override lateinit var fragmentHelper: BaseUiHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}