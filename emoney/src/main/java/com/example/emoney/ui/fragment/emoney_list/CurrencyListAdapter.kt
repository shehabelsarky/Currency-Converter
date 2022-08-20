package com.example.emoney.ui.fragment.emoney_list

import android.content.Context
import com.example.emoney.databinding.ItemCurrencyBinding
import com.example.emoney.domain.entity.latest.local.LatestCurrencyRate
import com.examples.core.ui.adapter.diffutilsAdapter.BaseRecyclerAdapter

/**
 * Created by Shehab Elsarky.
 */
class CurrencyListAdapter(
    private var mListener:(LatestCurrencyRate)->Unit
) : BaseRecyclerAdapter<ItemCurrencyBinding, LatestCurrencyRate>(ItemCurrencyBinding::inflate,
    { oldItem, newItem -> oldItem.countryCode == newItem.countryCode }) {

    override fun bind(
        context: Context,
        binding: ItemCurrencyBinding,
        item: LatestCurrencyRate,
        position: Int
    ) {
        binding.apply {
            tvCurrencyName.text = item.countryCode
            tvDate.text = item.date
            tvRate.text = item.rate

            root.setOnClickListener {
                mListener(item)
            }
        }
    }

}