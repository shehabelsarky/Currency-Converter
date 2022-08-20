package com.example.emoney.di

import com.example.emoney.data.restful.EMoneyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit


/**
 * Created by Shehab Elsarky.
 */
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    internal fun provideServicesApi(retrofit: Retrofit): EMoneyApi {
        return retrofit.create(EMoneyApi::class.java)
    }
}
