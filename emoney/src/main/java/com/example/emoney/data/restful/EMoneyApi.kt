package com.example.emoney.data.restful

import com.example.emoney.domain.entity.latest.response.LatestCurrencyRateResponse
import com.examples.core.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Shehab ELsarky.
 */
interface EMoneyApi {

    @GET(BuildConfig.baseUrl + Config.LATEST)
    suspend fun getLatestCurrencyRate(
        @Query("symbols") symbols: String, @Query("base") base: String
    ): LatestCurrencyRateResponse
}