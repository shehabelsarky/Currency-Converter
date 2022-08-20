package com.example.emoney.domain.entity.convert.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Query {
    @SerializedName("amount")
    @Expose
    var amount: Int? = null

    @SerializedName("from")
    @Expose
    var from: String? = null

    @SerializedName("to")
    @Expose
    var to: String? = null
}