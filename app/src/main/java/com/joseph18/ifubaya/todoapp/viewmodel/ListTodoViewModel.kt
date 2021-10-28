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

class ListTodoViewModel(application :Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val todoLD = MutableLiveData<List<Todo>>()

    fun refresh() {
        launch {
            val db = buildDb(getApplication())

            todoLD.value = db.todoDao().selectUndoneTodo()
        }
    }

    fun clearTask(todo :Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().updateIsDoneByUuid(todo.isDone, todo.uuid)

            todoLD.value = db.todoDao().selectUndoneTodo()
        }
    }
}