package com.example.emoney.domain.entity.convert.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Info {
    @SerializedName("rate")
    @Expose
    var rate: Double? = null

    @SerializedName("timestamp")
    @Expose
    var timestamp: Int? = null
}