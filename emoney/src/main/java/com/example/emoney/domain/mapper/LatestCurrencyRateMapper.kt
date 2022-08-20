package com.example.emoney.domain.mapper

import com.example.emoney.domain.entity.latest.local.LatestCurrencyRate
import com.example.emoney.domain.entity.latest.remote.RemoteLatestCurrencyRate
import com.examples.core.domain.usecase.base.ModelMapper
import com.examples.core.domain.utils.getCurrentDate
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

/**
 * Created by Shehab Elsarky.
 */
class LatestCurrencyRateMapper @Inject constructor() :
    ModelMapper<RemoteLatestCurrencyRate, List<LatestCurrencyRate>> {

    override fun convert(from: RemoteLatestCurrencyRate?): List<LatestCurrencyRate> {
        val currentDate = from?.date
        val latestCurrencyRateList: List<LatestCurrencyRate>? = getRates(from?.rates)
            ?.entries
            ?.map { e ->
                val latestCurrencyRate = LatestCurrencyRate()
                latestCurrencyRate.apply {
                    date = currentDate?: getCurrentDate()
                    countryCode = e.key
                    rate = e.value
                }
                latestCurrencyRate
            }
        return latestCurrencyRateList ?: emptyList()
    }

    private fun getRates(ratesElement: JsonElement?): Map<String, String>? {
        val type: Type = object :
            TypeToken<Map<String, String>>() {}.type
        return Gson().fromJson(ratesElement, type)
    }

}