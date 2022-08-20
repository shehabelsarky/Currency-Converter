package com.example.emoney.ui.fragment.emoney_list

import android.os.Bundle
import android.view.View
import com.example.emoney.databinding.FragmentEMoneyBinding
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

    override lateinit var viewModel: EMoneyListViewModel
    override lateinit var fragmentHelper: BaseUiHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}