package com.joseph18.ifubaya.todoapp.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.joseph18.ifubaya.todoapp.model.TodoDatabase

const val DB_NAME = "todoDb"

fun buildDb(context: Context): TodoDatabase {
    return Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME).build()
}

val MIGRATION_1_2 = object :Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Todo ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
    }
}