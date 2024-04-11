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
import java.util.Calendar

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


    override fun updateContactsGroupApi(id:Int?, usersGroup:String?, context: Context) {
        val call: Call<ResponseBody?>? = ApiClient.instance?.api?.updateContactsGroupApi(id, usersGroup)
        call?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Toast.makeText(context, "Группа Обновлена", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(context, "ВКЛЮЧИТЕ ИНТЕРНЕТ", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun loadInfoNot (user:String, context: Context) {

        val calendar: Calendar = Calendar.getInstance()
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        var month: Int = calendar.get(Calendar.MONTH)
        month++
        val year:Int = calendar.get(Calendar.YEAR)

        val call = ApiClient.instance?.api?.loadGroupsNot(user)



        call?.enqueue(object: Callback<ArrayList<GroupApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<GroupApiModel>>,
                response: Response<ArrayList<GroupApiModel>>
            ) {


                var loadGroups: ArrayList<GroupApiModel>? = null

                loadGroups?.clear()

                loadGroups = (response.body() as ArrayList<GroupApiModel>?)!!

                loadGroups?.map {


                    loadEventsNot(day.toString(), month.toString(), year.toString(), it.id.toString(), context)


                }




            }

            override fun onFailure(call: Call<ArrayList<GroupApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()

            }
        })

    }

    fun loadEventsNot (day:String, month:String, year:String, idGroup:String, context: Context) {

        val call = ApiClient.instance?.api?.loadEventsNot(day, month, year, idGroup)



        call?.enqueue(object: Callback<ArrayList<EventApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<EventApiModel>>,
                response: Response<ArrayList<EventApiModel>>
            ) {


                var loadEvents: ArrayList<EventApiModel>? = null

                loadEvents?.clear()

                loadEvents = (response.body() as ArrayList<EventApiModel>?)!!


                if(loadEvents.count()>0) {

                    var day_notif:String?=null
                    var month_notif:String?=null
                    var year_notif:String?=null
                    var time_notif:String?=null

                    loadEvents?.map {
                        day_notif = it.day.toString()
                        month_notif = it.month.toString()
                        year_notif = it.year.toString()
                        time_notif = it.timeStart.toString()

                        Toast.makeText(context, " У Вас ${day_notif}.${month_notif}.${year_notif}г. в ${time_notif} запланировано мероприятие", Toast.LENGTH_LONG).show()

                    }

                }

            }

            override fun onFailure(call: Call<ArrayList<EventApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()

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


    override fun deleteGroup(id:Int, context:Context) {

        val call: Call<ResponseBody?>? = ApiClient.instance?.api?.deleteGroup(id)

        call?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Toast.makeText(
                    context,
                    "ГРУППА УДАЛЕНА",
                    Toast.LENGTH_SHORT
                ).show()


            }



            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(
                    context,
                    "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!",
                    Toast.LENGTH_SHORT
                ).show()
            }


        })

    }
}