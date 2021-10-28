package com.joseph18.ifubaya.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Todo::class, ), version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() :TodoDao

    companion object {
        @Volatile
        private var instance :TodoDatabase ?= null

        private val LOCK = Any()

        private fun buildDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "todoDb" //database name
        ).build()

        //synchronized(LOCK) is to make it singleton (only one instance)
        operator fun invoke(context :Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}