package com.example.sheduller.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sheduller.data.models.EventModel
import com.example.sheduller.data.models.GroupModel

@Dao
interface GroupsDao {

    @Insert
    suspend fun insert(model: GroupModel)

    @Query("SELECT * FROM groups_data_table")
    fun loadGroups(): LiveData<List<GroupModel>>

    @Query("SELECT * FROM groups_data_table  WHERE groups_name LIKE '%' || :name || '%'" )
    fun loadSearchGroups(name:String): LiveData<List<GroupModel>>

    @Query("SELECT groups_users_group FROM groups_data_table  WHERE groups_admin = :admin AND groups_id = :idGroup" )
    fun loadContactsGroup(admin:String, idGroup:Int): LiveData<List<String>>

    @Update
    suspend fun updateContactsGroup(model: GroupModel)

    @Delete
    suspend fun deleteGroup(model: GroupModel)


//    @Query("SELECT * FROM groups_data_table  WHERE groups_admin = :admin" )
//    fun loadGroupsAdmin(admin:String): LiveData<List<GroupModel>>
//
//    @Query("SELECT * FROM groups_data_table WHERE groups_admin != :admin" )
//    fun loadGroupsUser(admin:String): LiveData<List<GroupModel>>

//    @Query("SELECT * FROM places_data_table WHERE places_id = :idPlace")
//    fun loadInfoPlace(idPlace:Int): LiveData<List<PlaceModel>>

//    @Query("SELECT * FROM category_data_table WHERE category_id IN (:idsCategories)")
//    fun loadCategoriesProduct(idsCategories:List<Int>): LiveData<List<CategoriesModel>>

    @Query("DELETE FROM groups_data_table")
    suspend fun clear()


}