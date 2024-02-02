package com.example.sheduller.data.repository.dataSource

import androidx.lifecycle.LiveData
import com.example.sheduller.data.models.GroupModel

interface GroupsDataSource {
    fun insert(model: GroupModel)
    fun loadGroups(): LiveData<List<GroupModel>>
    fun loadSearchGroups(name:String): LiveData<List<GroupModel>>
    fun loadContactsGroup(admin:String, idGroup:Int): LiveData<List<String>>
    fun updateContactsGroup(model: GroupModel)


    suspend fun clear()
}