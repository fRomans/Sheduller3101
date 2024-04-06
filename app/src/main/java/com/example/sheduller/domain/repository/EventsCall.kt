package com.example.sheduller.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sheduller.data.models.EventModel

interface EventsCall {
    fun loadEventsDay(day:Int, month:Int, year:Int, groupId:Int): LiveData<List<EventModel>>
    suspend fun startMigration(context: Context)
    fun createEvent(
        day: Int?, month: Int?, year: Int?, timeStart: String?, timeEnd: String?,
        description: String?,
        groupId: Int?, dayNot: Int?, monthNot: Int?, yearNot: Int?, context: Context
    )
}