package com.joseph18.ifubaya.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity//(tableName = "todo")
data class Todo(
    //@ColumnInfo(name = "title")
    var title :String,
    var notes :String,
) {
    @PrimaryKey(autoGenerate = true)
    var uuid :Int = 0
}