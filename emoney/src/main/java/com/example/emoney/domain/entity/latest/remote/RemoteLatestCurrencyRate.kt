package com.example.emoney.domain.entity.latest.remote

import com.google.gson.JsonElement
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RemoteLatestCurrencyRate {
    @SerializedName("base")
    @Expose
    var base: String? = null

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("rates")
    @Expose
    var rates: JsonElement? = null

    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("timestamp")
    @Expose
    var timestamp: Int? = null
}