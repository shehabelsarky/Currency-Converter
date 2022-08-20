package com.example.emoney.ui.fragment.emoney_update

import android.content.Context
import android.view.inputmethod.EditorInfo
import com.example.emoney.databinding.ItemCalculateCurrencyBinding
import com.example.emoney.domain.entity.latest.local.LatestCurrencyRate
import com.examples.core.ui.adapter.diffutilsAdapter.BaseRecyclerAdapter


/**
 * Created by Shehab Elsarky.
 */
class CurrencyUpdateListAdapter(
    private var mListener: (text: String, position: Int) -> Unit
) :
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
            tvCurrencyName.text = item.countryCode
            etRate.setText(item.rate)
        }
    }
}