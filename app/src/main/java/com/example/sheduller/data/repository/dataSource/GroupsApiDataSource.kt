package com.example.sheduller.data.repository.dataSource

import android.content.Context

interface GroupsApiDataSource {
    fun createGroup(name: String?, admin:String?, usersAdmin:String?, context: Context)
    fun startMigration (user:String, context: Context)
}