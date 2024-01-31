package com.example.sheduller.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GroupApiModel (
    //получение данных с сервера
    @SerializedName("id") @Expose
    var id: Int? = null,
    @SerializedName("name") @Expose
    var name: String? = null,
    @SerializedName("admin") @Expose
    var admin: String? = null,
    @SerializedName("users_group") @Expose
    var usersGroup: String? = null
)