package com.example.sheduller.data.repository.dataSourceImpl

import androidx.lifecycle.LiveData
import com.example.sheduller.data.localDB.EventsDao
import com.example.sheduller.data.localDB.GroupsDao
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.models.GroupModel
import com.example.sheduller.data.repository.dataSource.EventsDataSource
import com.example.sheduller.data.repository.dataSource.GroupsDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroupsDataSourceImpl (private val dao: GroupsDao):
    GroupsDataSource {


    override fun insert(model: GroupModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(model)}
    }

    override fun loadGroups(): LiveData<List<GroupModel>> {
        return dao.loadGroups()
    }

    override fun loadSearchGroups(name:String): LiveData<List<GroupModel>> {
        return dao.loadSearchGroups(name)
    }

    override fun loadContactsGroup(admin:String, idGroup:Int): LiveData<List<String>> {
        return dao.loadContactsGroup(admin, idGroup)
    }



    override suspend fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.clear()}
    }



}