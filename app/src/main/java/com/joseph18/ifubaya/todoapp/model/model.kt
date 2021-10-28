package com.joseph18.ifubaya.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity//(tableName = "todo")
data class Todo(

    var title :String,

    var notes :String,

    @ColumnInfo(name = "priority")
    var priorityLevel :Int,
) {
    @PrimaryKey(autoGenerate = true)
    var uuid :Int = 0
}