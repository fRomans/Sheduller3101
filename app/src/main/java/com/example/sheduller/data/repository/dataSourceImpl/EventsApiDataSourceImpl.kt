package com.example.sheduller.data.repository.dataSourceImpl

import android.content.Context
import android.widget.Toast
import com.example.sheduller.data.api.ApiClient
import com.example.sheduller.data.models.EventApiModel
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.repository.dataSource.EventsApiDataSource
import com.example.sheduller.data.repository.dataSource.EventsDataSource
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsApiDataSourceImpl (private val dataSource: EventsDataSource):
    EventsApiDataSource {

    override fun createEvent(day: Int?,month: Int?,year: Int?,timeStart: String?,timeEnd: String?,
                             description: String?,
                             groupId: Int?, context: Context){

        val call: Call<ResponseBody?>? = ApiClient.instance?.api?.createEvent(day, month, year, timeStart,
        timeEnd, description, groupId)
        call?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Toast.makeText(context, "Группа Создана", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(context, "ВКЛЮЧИТЕ ИНТЕРНЕТ", Toast.LENGTH_SHORT).show()
            }
        })
    }


// для получения данных с бд серв и миграция в локальную бд
    override fun startMigration (context: Context) {
//получение данных с сервера
        val call = ApiClient.instance?.api?.loadEvents()



        call?.enqueue(object: Callback<ArrayList<EventApiModel>> {
            override fun onResponse(
                call: Call<ArrayList<EventApiModel>>,
                response: Response<ArrayList<EventApiModel>>
            ) {


                var loadEvents: ArrayList<EventApiModel>? = null // объект для получения данных с серв

                loadEvents?.clear() // очистка этого объекта

                loadEvents = (response.body() as ArrayList<EventApiModel>?)!!   //запись в объект данных с сервера

                for (audit in loadEvents) {

                    audit.id?.let {
                        EventModel(
                            it,
                            audit.day!!,
                            audit.month!!,
                            audit.year!!,
                            audit.timeStart.toString(),
                            audit.timeEnd.toString(),
                            audit.description.toString(),
                            audit.groupId!!
                        )
                    }?.let {
                        dataSource.insert(
                            it
                        )
                    }

                }

                Toast.makeText(context, "ЗАГРУЗКА", Toast.LENGTH_SHORT).show()


           }

            override fun onFailure(call: Call<ArrayList<EventApiModel>>, t: Throwable) {
                Toast.makeText(context, "ОШИБКА! ВКЛЮЧИТЕ ИНТЕРНЕТ!", Toast.LENGTH_SHORT).show()

            }
        })

    }

}