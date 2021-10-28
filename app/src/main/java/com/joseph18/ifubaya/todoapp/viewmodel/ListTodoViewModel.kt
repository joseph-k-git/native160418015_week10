package com.joseph18.ifubaya.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.joseph18.ifubaya.todoapp.model.Todo
import com.joseph18.ifubaya.todoapp.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application :Application) : AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<List<Todo>>()

    fun refresh() {
        launch {
            val db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java, "todoDb").build()

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun clearTask(todo :Todo) {
        launch {
            val db = Room.databaseBuilder(getApplication(), TodoDatabase::class.java, "todoDb").build()
            db.todoDao().deleteTodo(todo)

            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}