package com.example.sheduller.data.repository.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.models.GroupModel
import com.example.sheduller.data.repository.dataSource.AuthenticationApiDataSource
import com.example.sheduller.data.repository.dataSource.EventsApiDataSource
import com.example.sheduller.data.repository.dataSource.EventsDataSource
import com.example.sheduller.data.repository.dataSource.GroupsApiDataSource
import com.example.sheduller.data.repository.dataSource.GroupsDataSource
import com.example.sheduller.domain.repository.AuthenticationCall
import com.example.sheduller.domain.repository.GroupsCall

class GroupsRepository (private val apiDataSource: GroupsApiDataSource,
                        private val dataSource: GroupsDataSource
):
    GroupsCall {
    override fun createGroup(name: String?, admin:String?, usersAdmin:String?, context: Context) {
        apiDataSource.createGroup(name, admin, usersAdmin, context)
    }

    override fun updateContactsGroupApi(id:Int?, usersGroup:String?, context: Context) {
        apiDataSource.updateContactsGroupApi(id, usersGroup, context)
    }


    override fun updateContactsGroup(model: GroupModel) {
        dataSource.updateContactsGroup(model)
    }

    override fun loadGroups(): LiveData<List<GroupModel>> {
        return dataSource.loadGroups()
    }

    override fun loadSearchGroups(name:String): LiveData<List<GroupModel>> {
        return dataSource.loadSearchGroups(name)
    }

    override fun loadContactsGroup(admin:String, idGroup:Int): LiveData<List<String>> {
        return dataSource.loadContactsGroup(admin, idGroup)
    }


    override suspend fun startMigration(user:String, context: Context) {
        dataSource.clear()
        apiDataSource.startMigration(user, context)
    }
}