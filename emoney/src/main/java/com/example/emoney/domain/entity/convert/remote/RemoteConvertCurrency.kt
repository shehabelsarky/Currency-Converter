package com.example.emoney.domain.entity.convert.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RemoteConvertCurrency {
    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("historical")
    @Expose
    var historical: Boolean? = null

    @SerializedName("info")
    @Expose
    var info: Info? = null

    @SerializedName("query")
    @Expose
    var query: Query? = null

    @SerializedName("result")
    @Expose
    var result: Double? = null

    @SerializedName("success")
    @Expose
    var success: Boolean? = null
}