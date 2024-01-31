package com.example.sheduller.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups_data_table")
data class GroupModel (
//модель бд на телефоне
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "groups_id")
    var id : Int,

    @ColumnInfo(name = "groups_name")
    var name:String,

    @ColumnInfo(name = "groups_admin")
    var admin:String,

    @ColumnInfo(name = "groups_users_group")
    var usersGroup:String
)