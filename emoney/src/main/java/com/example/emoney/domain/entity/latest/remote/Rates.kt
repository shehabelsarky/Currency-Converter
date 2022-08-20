package com.example.emoney.domain.entity.latest.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Rates {
    @SerializedName("EGP")
    @Expose
    var egp: Double? = null

    @SerializedName("GBP")
    @Expose
    var gbp: Double? = null

    @SerializedName("JPY")
    @Expose
    var jpy: Double? = null

    @SerializedName("SAR")
    @Expose
    var sar: Double? = null

    @SerializedName("USD")
    @Expose
    var usd: Double? = null
}