package com.joseph18.ifubaya.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.joseph18.ifubaya.todoapp.model.Todo
import com.joseph18.ifubaya.todoapp.model.TodoDatabase
import com.joseph18.ifubaya.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application :Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val todoLD = MutableLiveData<Todo>()

    fun addTodo(todo :Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(todo)
        }
    }

    fun fetchTodo(uuid :Int) {
        launch {
            val db = buildDb(getApplication())
            todoLD.value = db.todoDao().selectTodoByUuid(uuid)
        }
    }

    fun updateTodo(title :String, notes :String, priorityLevel :Int, uuid :Int) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().updateTodoByUuid(title, notes, priorityLevel, uuid)
        }
    }
}