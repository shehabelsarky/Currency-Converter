package com.example.emoney.data.source

import com.example.emoney.domain.entity.latest.response.LatestCurrencyRateResponse


/**
 * Created by Shehab Elsarky.
 */
interface EMoneyRemoteDataSource {

    suspend fun getLatestCurrencyRate(
        symbols: String, base: String
    ): LatestCurrencyRateResponse
}