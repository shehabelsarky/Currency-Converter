package com.example.emoney.domain.usecase

import com.example.emoney.data.repository.EMoneyRepository
import com.example.emoney.domain.entity.latest.local.LatestCurrencyRate
import com.example.emoney.domain.entity.latest.query.LatestCurrencyQuery
import com.example.emoney.domain.entity.latest.remote.RemoteLatestCurrencyRate
import com.example.emoney.domain.entity.latest.response.LatestCurrencyRateResponse
import com.example.emoney.domain.mapper.LatestCurrencyRateMapper
import com.examples.core.data.mapper.CloudErrorMapper
import com.examples.core.domain.usecase.base.RemoteUseCase
import javax.inject.Inject

/**
 * Created by Shehab Elsarky.
 */
class LatestCurrencyRateUseCase @Inject constructor(
    errorUtil: CloudErrorMapper,
    private val eMoneyRepository: EMoneyRepository,
    private val mapper: LatestCurrencyRateMapper
) : RemoteUseCase<LatestCurrencyQuery, RemoteLatestCurrencyRate, List<LatestCurrencyRate>>(
    errorUtil
) {
    public override suspend fun executeOnBackground(parameters: LatestCurrencyQuery): RemoteLatestCurrencyRate {
        return eMoneyRepository.getLatestCurrencyRate(
            symbols = parameters.symbols,
            base = parameters.base
        )
    }

    public override suspend fun convert(dto: RemoteLatestCurrencyRate): List<LatestCurrencyRate> {
        return mapper.convert(dto)
    }
}