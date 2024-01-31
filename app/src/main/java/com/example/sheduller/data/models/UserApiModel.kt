package com.example.sheduller.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserApiModel (

    //получение данных с сервера
    @SerializedName("id") @Expose
    var id: Int? = null,
    @SerializedName("name_account") @Expose
    var nameAccount: String? = null,
    @SerializedName("pin_code") @Expose
    var pinCode: String? = null,
    @SerializedName("role") @Expose
    var role: String? = null
)