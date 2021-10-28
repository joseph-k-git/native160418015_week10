package com.joseph18.ifubaya.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.joseph18.ifubaya.todoapp.R
import com.joseph18.ifubaya.todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList :ArrayList<Todo>, val adapterOnClick :(Todo) -> Unit) :
    RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {
    class TodoListViewHolder(var view :View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        holder.view.checkTask.text = todoList[position].title + " " + todoList[position].priorityLevel.toString()

        holder.view.checkTask.setOnCheckedChangeListener() {
            compoundButton, // compoundButton is the checkTask itself
            isChecked -> run {
                if (isChecked) {
                    todoList[position].isDone = 1
                    adapterOnClick(todoList[position])
                }
            }
        }

        holder.view.imgEdit.setOnClickListener() {
            val action = TodoListFragmentDirections.actionEditTodoFragmentFromTodoListFragment(uuid = todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList :List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}