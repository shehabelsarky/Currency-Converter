package com.example.emoney.data.repository

import com.example.emoney.data.source.EMoneyRemoteDataSource
import com.example.emoney.domain.entity.latest.response.LatestCurrencyRateResponse
import javax.inject.Inject

/**
 * Created by Shehab Elsarky.
 */
class EMoneyRepositoryImp @Inject constructor(
    private val eMoneyRemoteDataSource: EMoneyRemoteDataSource
) : EMoneyRepository {

    override suspend fun getLatestCurrencyRate(
        symbols: String,
        base: String
    ): LatestCurrencyRateResponse {
        return eMoneyRemoteDataSource.getLatestCurrencyRate(symbols = symbols, base = base)
    }
}
