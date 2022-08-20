package com.example.emoney.di

import com.example.emoney.data.repository.EMoneyRepository
import com.example.emoney.data.repository.EMoneyRepositoryImp
import com.example.emoney.data.source.EMoneyRemoteDataSource
import com.example.emoney.data.source.EMoneyRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Shehab Elsarky.
 */
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class EMoneyModule {
    @Binds
    abstract fun bindEMoneyRepository(
        eMoneyRepositoryImp: EMoneyRepositoryImp
    ): EMoneyRepository

    @Binds
    abstract fun bindEMoneyRemoteDataSource(
        eMoneyRemoteDataSourceImpl: EMoneyRemoteDataSourceImpl
    ): EMoneyRemoteDataSource
}