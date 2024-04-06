package com.example.sheduller.data.repository.dataSource

import android.content.Context

interface EventsApiDataSource {
    fun createEvent(
        day: Int?, month: Int?, year: Int?, timeStart: String?, timeEnd: String?,
        description: String?,
        groupId: Int?, dayNot: Int?, monthNot: Int?, yearNot: Int?, context: Context
    )

    fun startMigration(context: Context)
}