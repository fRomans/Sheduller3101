package com.example.sheduller.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.models.GroupModel
import com.example.sheduller.domain.repository.AuthenticationCall
import com.example.sheduller.domain.repository.GroupsCall

class GroupsUseCase(private val call: GroupsCall) {
    fun createGroup (name: String?, admin:String?, usersAdmin:String?, context: Context) {

        call.createGroup(name, admin, usersAdmin, context)

    }

    fun updateContactsGroupApi(id:Int?, usersGroup:String?, context: Context) {

        call.updateContactsGroupApi(id, usersGroup, context)

    }



    fun updateContactsGroup (model: GroupModel) {

        call.updateContactsGroup(model)

    }

    suspend fun deleteGroup (model: GroupModel) {

        call.deleteGroup(model)

    }

    fun deleteGroupApi(id:Int, context: Context) {

        call.deleteGroupApi(id, context)

    }

    fun loadGroups(): LiveData<List<GroupModel>> {

        return call.loadGroups()

    }

    fun loadSearchGroups(name:String): LiveData<List<GroupModel>> {

        return call.loadSearchGroups(name)

    }

    fun loadContactsGroup(admin:String, idGroup:Int): LiveData<List<String>> {

        return call.loadContactsGroup(admin, idGroup)

    }


    suspend fun startMigration (user:String, context: Context) {

        call.startMigration(user, context)

    }
}