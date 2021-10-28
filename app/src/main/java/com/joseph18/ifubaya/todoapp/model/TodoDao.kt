package com.joseph18.ifubaya.todoapp.model

import androidx.room.*

@Dao
interface TodoDao {

    // suspend fun can be suspended and continued by the coroutine handler
    // vararg for multiple Todos
    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo :Todo)

    @Query("SELECT * FROM todo")
    suspend fun selectAllTodo() :List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodoByUuid(id :Int) :Todo

    @Delete
    suspend fun deleteTodo(todo :Todo)
}