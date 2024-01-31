package com.example.sheduller.data.repository.dataSourceImpl

import android.content.Context
import android.widget.Toast
import com.example.sheduller.data.api.ApiClient
import com.example.sheduller.data.models.EventApiModel
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.models.GroupApiModel
import com.example.sheduller.data.models.GroupModel
import com.example.sheduller.data.repository.dataSource.EventsDataSource
import com.example.sheduller.data.repository.dataSource.GroupsApiDataSource
import com.example.sheduller.data.repository.dataSource.GroupsDataSource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GroupsApiDataSourceImpl(private val dataSource: GroupsDataSource):GroupsApiDataSource {

    override fun createGroup(name: String?, admin:String?, usersAdmin:String?, context: Context) {
        val call: Call<ResponseBody?>? = ApiClient.instance?.api?.createGroup(name, admin, usersAdmin)
        call?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Toast.makeText(context, "Группа Создана", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(context, "ВКЛЮЧИТЕ ИНТЕРНЕТ", Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun startMigration (user:String, context: Context) {

        val call = ApiClient.instance?.api?.loadGroups(user)



        call?.enqueue(object: Callback<ArrayList<GroupApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<GroupApiModel>>,
                response: Response<ArrayList<GroupApiModel>>
            ) {


                var loadGroups: ArrayList<GroupApiModel>? = null

                loadGroups?.clear()

                loadGroups = (response.body() as ArrayList<GroupApiModel>?)!!

                for (audit in loadGroups) {

                    audit.id?.let {
                        GroupModel(
                            it,
                            audit.name.toString(),
                            audit.admin.toString(),
                            audit.usersGroup.toString()
                        )
                    }?.let {
                        dataSource.insert(
                            it
                        )
                    }

                }

                Toast.makeText(context, "ЗАГРУЗКА", Toast.LENGTH_SHORT).show()


            }

            override fun onFailure(call: Call<ArrayList<GroupApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()

            }
        })

    }
}