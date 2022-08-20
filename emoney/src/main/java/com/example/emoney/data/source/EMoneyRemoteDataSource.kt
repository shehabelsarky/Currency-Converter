package com.example.emoney.data.source

import com.example.emoney.domain.entity.latest.remote.RemoteLatestCurrencyRate


/**
 * Created by Shehab Elsarky.
 */
interface EMoneyRemoteDataSource {

    suspend fun getLatestCurrencyRate(
        symbols: String, base: String
    ): RemoteLatestCurrencyRate
}