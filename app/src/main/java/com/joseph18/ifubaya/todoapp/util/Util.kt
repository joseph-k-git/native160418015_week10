package com.joseph18.ifubaya.todoapp.util

import android.content.Context
import androidx.room.Room
import com.joseph18.ifubaya.todoapp.model.TodoDatabase

val DB_NAME = "todoDb"

fun buildDb(context :Context) :TodoDatabase {
    val db = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME).build()
    return db
}