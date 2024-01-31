package com.example.sheduller.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sheduller.data.models.EventModel

@Dao
interface EventsDao {
// работа с бд на телефоне

    @Insert
    suspend fun insert(model: EventModel)

//    @Query("SELECT * FROM places_data_table")
//    fun loadPlaces(): LiveData<List<PlaceModel>>


//LiveData — это класс, который хранит объект данных и позволяет наблюдателям получать обновления при каждом изменении данных.
    @Query("SELECT * FROM events_data_table WHERE events_day = :day AND events_month = :month AND events_year = :year AND events_group_id = :groupId")
    fun loadEventsDay(day:Int, month:Int, year:Int, groupId:Int): LiveData<List<EventModel>>

//    @Query("SELECT * FROM places_data_table WHERE places_id = :idPlace")
//    fun loadInfoPlace(idPlace:Int): LiveData<List<PlaceModel>>

//    @Query("SELECT * FROM category_data_table WHERE category_id IN (:idsCategories)")
//    fun loadCategoriesProduct(idsCategories:List<Int>): LiveData<List<CategoriesModel>>

    @Query("DELETE FROM events_data_table")
    suspend fun clear()


}