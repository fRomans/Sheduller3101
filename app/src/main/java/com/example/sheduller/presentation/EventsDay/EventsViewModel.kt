package com.example.sheduller.presentation.EventsDay

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.domain.useCase.EventsUseCase
import kotlinx.coroutines.launch

class EventsViewModel (private val useCase: EventsUseCase):
    ViewModel() {


    fun createEvent(
        day: Int?, month: Int?, year: Int?, timeStart: String?, timeEnd: String?,
        description: String?,
        groupId: Int?, context: Context
    ){
        useCase.createEvent(day, month, year, timeStart, timeEnd, description, groupId, context)
    }


    fun loadEventsDay (day:Int, month:Int, year:Int, groupId:Int):      //получение данных из лок бд
            LiveData<List<EventModel>> {
        return useCase.loadEventsDay(day, month, year,groupId)
    }



    fun migration(context: Context) = viewModelScope.launch {   // получение данных с сервера
        useCase.startMigration(context)

    }


}