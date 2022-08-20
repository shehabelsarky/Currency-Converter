package com.example.emoney.data.source

import com.example.emoney.data.restful.EMoneyApi
import com.example.emoney.domain.entity.latest.remote.RemoteLatestCurrencyRate
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
    ): RemoteLatestCurrencyRate {
        return eMoneyApi.getLatestCurrencyRate(symbols = symbols, base = base)
    }
}
