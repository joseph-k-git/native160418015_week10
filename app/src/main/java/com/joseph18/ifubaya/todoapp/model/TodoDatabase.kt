package com.joseph18.ifubaya.todoapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joseph18.ifubaya.todoapp.util.DB_NAME
import com.joseph18.ifubaya.todoapp.util.MIGRATION_1_2
import com.joseph18.ifubaya.todoapp.util.MIGRATION_2_3

@Database(entities = arrayOf(Todo::class, ), version = 1) //, version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() :TodoDao

    companion object {
        @Volatile
        private var instance :TodoDatabase ?= null

        private val LOCK = Any()

        private fun buildDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            DB_NAME,
        )   //.addMigrations(MIGRATION_1_2, MIGRATION_2_3) // if can't migrate, try to uninstall app and comment this line out and change version from 3 to 1
            .build()

        // synchronized(LOCK) is to make it singleton (only one instance)
        operator fun invoke(context :Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}