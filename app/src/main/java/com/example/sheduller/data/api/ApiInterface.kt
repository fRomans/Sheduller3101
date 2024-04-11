package com.example.sheduller.data.api

import com.example.sheduller.data.models.EventApiModel
import com.example.sheduller.data.models.GroupApiModel
import com.example.sheduller.data.models.UserApiModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    //сопряжение серв
    @GET("loadEvents.php")
    fun loadEvents(): Call<ArrayList<EventApiModel>>


    @FormUrlEncoded
    @POST("createUser.php")
    fun createUser(
        @Field("name_account") nameAccount: String?,
        @Field("pin_code") pinCode: String?,
        @Field("role") role: String?
    ): Call<ResponseBody?>?


    @GET("infoUser.php")
    fun infoUser(@Query("name_account") nameAccount: String): Call<ArrayList<UserApiModel>>

    @FormUrlEncoded
    @POST("createGroup.php")
    fun createGroup(
        @Field("name") name: String?,
        @Field("admin") admin: String?,
        @Field("users_group") usersGroup: String?
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("updateContactsGroup.php")
    fun updateContactsGroupApi(
        @Field("id") id: Int?,
        @Field("users_group") usersGroup: String?
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("deleteGroup.php")
    fun deleteGroup(
        @Field("id") id: Int?,
    ): Call<ResponseBody?>?

    @GET("loadGroups.php")
    fun loadGroups(@Query("user") user: String):
            Call<ArrayList<GroupApiModel>>

    @GET("loadGroupsNot.php")
    fun loadGroupsNot(@Query("user") user: String):
            Call<ArrayList<GroupApiModel>>
    @GET("loadEventsNot.php")
    fun loadEventsNot(@Query("day_not") day: String,@Query("month_not") month: String,@Query("year_not") year: String,@Query("group_id") groupId: String):
            Call<ArrayList<EventApiModel>>

    @FormUrlEncoded
    @POST("createEvent.php")
    fun createEvent(
        @Field("day") day: Int?,
        @Field("month") month: Int?,
        @Field("year") year: Int?,
        @Field("time_start") timeStart: String?,
        @Field("time_end") timeEnd: String?,
        @Field("description") description: String?,
        @Field("group_id") groupId: Int?,
        @Field("day_not") dayNot: Int?,
        @Field("month_not") monthNot: Int?,
        @Field("year_not") yearNot: Int?
    ): Call<ResponseBody?>?

}