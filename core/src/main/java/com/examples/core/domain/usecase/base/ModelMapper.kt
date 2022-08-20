package com.examples.core.domain.usecase.base

/**
 * Created by Shehab Elsarky.
 */
interface ModelMapper<From,To> {
    fun convert(from:From?):To
}