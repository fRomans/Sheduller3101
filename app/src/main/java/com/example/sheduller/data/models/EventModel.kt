package com.example.sheduller.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events_data_table")
data class EventModel(

    //модель бд на телефоне
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "events_id")
    var id: Int,

    @ColumnInfo(name = "events_day")
    var day: Int,

    @ColumnInfo(name = "events_month")
    var month: Int,

    @ColumnInfo(name = "events_year")
    var year: Int,

    @ColumnInfo(name = "events_time_start")
    var timeStart: String,

    @ColumnInfo(name = "events_time_end")
    var timeEnd: String,

    @ColumnInfo(name = "events_description")
    var description: String,

    @ColumnInfo(name = "events_group_id")
    var groupId: Int,

    @ColumnInfo(name = "events_day_not")
    var dayNot: Int,

    @ColumnInfo(name = "events_month_not")
    var monthNot: Int,

    @ColumnInfo(name = "events_year_not")
    var yearNot: Int

)