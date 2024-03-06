package com.example.sheduller.data.repository.dataSource

import android.content.Context
import com.example.sheduller.data.models.GroupModel

interface GroupsApiDataSource {
    fun createGroup(name: String?, admin:String?, usersAdmin:String?, context: Context)
    fun startMigration (user:String, context: Context)
    fun updateContactsGroupApi(id:Int?, usersGroup:String?, context: Context)
    fun deleteGroup(id:Int, context:Context)
}