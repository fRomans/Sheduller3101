package com.example.sheduller.data.repository.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.repository.dataSource.EventsApiDataSource
import com.example.sheduller.data.repository.dataSource.EventsDataSource
import com.example.sheduller.domain.repository.EventsCall

class EventsRepository(
    private val apiDataSource: EventsApiDataSource,
    private val dataSource: EventsDataSource
) : EventsCall {

// объедниняем действия EventsApiDataSource и EventsDataSource

    override fun createEvent(
        day: Int?, month: Int?, year: Int?, timeStart: String?, timeEnd: String?,
        description: String?,
        groupId: Int?, dayNot: Int?, monthNot: Int?, yearNot: Int?, context: Context
    ) {
        apiDataSource.createEvent(day, month, year, timeStart, timeEnd,
        description, groupId,dayNot, monthNot, yearNot, context)
    }

    override fun loadEventsDay(
        day: Int,
        month: Int,
        year: Int,
        groupId: Int
    ): LiveData<List<EventModel>> {
        return dataSource.loadEventsDay(day, month, year, groupId)
    }


    override suspend fun startMigration(context: Context) {
        dataSource.clear()
        apiDataSource.startMigration(context)
    }

}