package com.joseph18.ifubaya.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.joseph18.ifubaya.todoapp.model.Todo

interface TodoCheckedChangeListener {
    fun onTodoCheckedChange(cb :CompoundButton, isChecked :Boolean, obj :Todo)
}

interface TodoEditClickListener {
    fun onTodoEditClick(view :View)
}

interface RadioClickListener {
    fun onRadioClick(view :View, obj :Todo)
}

interface TodoSaveChangesClickListener {
    fun onTodoSaveChangesClick(view :View, obj :Todo)
}