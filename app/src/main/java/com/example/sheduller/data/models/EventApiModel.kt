package com.example.sheduller.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EventApiModel (

    //получение данных с сервера
    @SerializedName("id") @Expose
    var id: Int? = null,
    @SerializedName("day") @Expose
    var day: Int? = null,
    @SerializedName("month") @Expose
    var month: Int? = null,
    @SerializedName("year") @Expose
    var year: Int? = null,
    @SerializedName("time_start") @Expose
    var timeStart: String? = null,
    @SerializedName("time_end") @Expose
    var timeEnd: String? = null,
    @SerializedName("description") @Expose
    var description: String? = null,
    @SerializedName("group_id") @Expose
    var groupId: Int? = null
)