package com.example.sheduller.presentation.Groups

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.models.GroupModel
import com.example.sheduller.domain.useCase.AuthenticationUseCase
import com.example.sheduller.domain.useCase.GroupsUseCase
import kotlinx.coroutines.launch

class GroupsViewModel(private val useCase: GroupsUseCase):
    ViewModel() {
    fun createGroup(name: String?, admin:String?, usersAdmin:String?, context: Context) {
        useCase.createGroup(name, admin, usersAdmin, context)
    }

    fun updateContactsGroupApi(id:Int?, usersGroup:String?, context: Context) {
        useCase.updateContactsGroupApi(id, usersGroup, context)
    }

    fun updateContactsGroup(model: GroupModel) {
        useCase.updateContactsGroup(model)
    }

    fun loadGroups():
            LiveData<List<GroupModel>> {
        return useCase.loadGroups()
    }

    fun loadSearchGroups(name:String):
            LiveData<List<GroupModel>> {
        return useCase.loadSearchGroups(name)
    }

    fun deleteGroup(model: GroupModel) = viewModelScope.launch{

        useCase.deleteGroup(model)
    }

//    fun deleteGroup(model: GroupModel) {
//        useCase.deleteGroup(model)
//    }

    fun deleteGroupApi(id:Int, context:Context) {
        useCase.deleteGroupApi(id, context)
    }

    fun loadContactsGroup(admin:String, idGroup:Int):
            LiveData<List<String>> {
        return useCase.loadContactsGroup(admin, idGroup)
    }


    fun migration(user:String, context: Context) = viewModelScope.launch {
        useCase.startMigration(user, context)

    }

    fun loadInfoNot(user:String, context: Context) = viewModelScope.launch {
        useCase.loadInfoNot(user, context)

    }
}