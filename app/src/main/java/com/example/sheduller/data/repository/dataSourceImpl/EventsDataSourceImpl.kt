package com.example.sheduller.data.repository.dataSourceImpl

import androidx.lifecycle.LiveData
import com.example.sheduller.data.localDB.EventsDao
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.repository.dataSource.EventsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventsDataSourceImpl (private val dao: EventsDao):
    EventsDataSource {

// для работы с бд телеф
    override fun insert(model: EventModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(model)}
    }



    override fun loadEventsDay(day:Int, month:Int, year:Int, groupId:Int ): LiveData<List<EventModel>> {
        return dao.loadEventsDay(day, month, year, groupId)
    }



    override suspend fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.clear()}
    }



}