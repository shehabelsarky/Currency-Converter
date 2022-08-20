package com.example.emoney.data.source

import com.example.emoney.data.restful.EMoneyApi
import com.example.emoney.domain.entity.latest.response.LatestCurrencyRateResponse
import javax.inject.Inject

/**
 * Created by Shehab Elsarky.
 */
class EMoneyRemoteDataSourceImpl @Inject constructor(
    private val eMoneyApi: EMoneyApi
) : EMoneyRemoteDataSource {

    override suspend fun getLatestCurrencyRate(
        symbols: String,
        base: String
    ): LatestCurrencyRateResponse {
        return eMoneyApi.getLatestCurrencyRate(symbols = symbols, base = base)
    }
}
