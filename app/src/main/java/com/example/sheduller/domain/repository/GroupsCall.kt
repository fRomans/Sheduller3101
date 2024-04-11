package com.example.sheduller.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sheduller.data.models.GroupModel

interface GroupsCall {
    fun createGroup(name: String?, admin:String?, usersAdmin:String?, context: Context)
    fun loadGroups(): LiveData<List<GroupModel>>
    fun loadSearchGroups(name:String): LiveData<List<GroupModel>>
    fun loadContactsGroup(admin:String, idGroup:Int): LiveData<List<String>>
    suspend fun startMigration(user:String, context: Context)
    fun updateContactsGroupApi(id:Int?, usersGroup:String?, context: Context)
    fun updateContactsGroup(model: GroupModel)
    suspend fun deleteGroup(model: GroupModel)
    fun deleteGroupApi(id:Int, context: Context)
    suspend fun loadInfoNot(user:String, context: Context)
}