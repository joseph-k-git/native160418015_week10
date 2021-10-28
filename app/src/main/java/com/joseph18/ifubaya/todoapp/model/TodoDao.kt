package com.joseph18.ifubaya.todoapp.model

import androidx.room.*

@Dao
interface TodoDao {

    // suspend fun can be suspended and continued by the coroutine handler
    // vararg for multiple Todos
    @Insert//(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo :Todo)

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    suspend fun selectAllTodo() :List<Todo>

    @Query("SELECT * FROM todo WHERE is_done = 0 ORDER BY priority DESC")
    suspend fun selectUndoneTodo() :List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodoByUuid(id :Int) :Todo

    @Query("UPDATE todo SET title = :title, notes = :notes, priority = :priority WHERE uuid = :id")
    suspend fun updateTodoByUuid(title :String, notes :String, priority :Int,  id :Int)

    @Query("UPDATE todo SET is_done = :isDone WHERE uuid = :id")
    suspend fun updateIsDoneByUuid(isDone :Int, id :Int)

    @Delete
    suspend fun deleteTodo(todo :Todo)
}