package com.example.sheduller.data.repository.dataSource

import androidx.lifecycle.LiveData
import com.example.sheduller.data.models.EventModel

interface EventsDataSource {
    fun insert(model: EventModel)
    fun loadEventsDay(day:Int, month:Int, year:Int, groupId:Int): LiveData<List<EventModel>>
    suspend fun clear()
}