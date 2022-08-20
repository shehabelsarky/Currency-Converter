package com.example.emoney.ui.fragment.emoney_update

import android.content.Context
import com.example.emoney.databinding.ItemCalculateCurrencyBinding
import com.example.emoney.domain.entity.latest.local.LatestCurrencyRate
import com.examples.core.ui.adapter.diffutilsAdapter.BaseRecyclerAdapter
import com.examples.core.utils.getStringByIdName


/**
 * Created by Shehab Elsarky.
 */
class CurrencyUpdateListAdapter() :
    BaseRecyclerAdapter<ItemCalculateCurrencyBinding, LatestCurrencyRate>(
        ItemCalculateCurrencyBinding::inflate,
        { oldItem, newItem -> oldItem.countryCode == newItem.countryCode }) {

    override fun bind(
        context: Context,
        binding: ItemCalculateCurrencyBinding,
        item: LatestCurrencyRate,
        position: Int
    ) {
        binding.apply {
            tvCurrencyName.text = root.context.getStringByIdName(item.countryCode)
            etRate.setText(item.rate)
        }
    }
}