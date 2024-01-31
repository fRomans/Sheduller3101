package com.example.sheduller.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.domain.repository.EventsCall

class EventsUseCase(private val call: EventsCall) {


    fun createEvent(
        day: Int?, month: Int?, year: Int?, timeStart: String?, timeEnd: String?,
        description: String?,
        groupId: Int?, context: Context
    ){
        call.createEvent(day, month, year, timeStart, timeEnd,
            description, groupId, context)
    }


    fun loadEventsDay(day:Int, month:Int, year:Int, groupId:Int): LiveData<List<EventModel>> { //загрузка лок бд

        return call.loadEventsDay(day, month, year, groupId)

    }



    suspend fun startMigration (context: Context) {   // получение данных с сервера

        call.startMigration(context)

    }
}