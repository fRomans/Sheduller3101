package com.example.sheduller.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.models.GroupModel

@Database(entities = [EventModel::class, GroupModel::class], version = 1)
abstract class DbTsFwo: RoomDatabase() {
    //бд телефона
    abstract val eventsDao:EventsDao
    abstract val groupsDao:GroupsDao
}







