package com.example.emoney.data.repository

import com.example.emoney.domain.entity.latest.response.LatestCurrencyRateResponse

/**
 * Created by Shehab Elsarky.
 */
interface EMoneyRepository {
    suspend fun getLatestCurrencyRate(
        symbols: String, base: String
    ): LatestCurrencyRateResponse
}