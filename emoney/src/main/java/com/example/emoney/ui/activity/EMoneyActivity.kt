package com.example.emoney.ui.activity

import com.example.emoney.R
import com.examples.core.ui.activity.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Shehab Elsarky.
 */
@AndroidEntryPoint
class EMoneyActivity : BaseActivity() {
    override var navGraphResourceId: Int = R.navigation.emoney_nav_graph
}