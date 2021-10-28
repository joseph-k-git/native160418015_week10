package com.joseph18.ifubaya.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.joseph18.ifubaya.todoapp.R
import com.joseph18.ifubaya.todoapp.model.Todo
import com.joseph18.ifubaya.todoapp.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

class CreateTodoFragment : Fragment() {

    private lateinit var viewModel :DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        btnCreateTodo.setOnClickListener() {
            val selectedRadioButton = view.findViewById<RadioButton>(radioGroupPirority.checkedRadioButtonId)

            var todo = Todo(
                title = txtTodoTitle.text.toString(),
                notes = txtTodoNotes.text.toString(),
                priorityLevel = Integer.parseInt(selectedRadioButton.tag.toString()),
                isDone = 0
            )
            viewModel.addTodo(todo)

            Toast.makeText(it.context, "ToDo Created", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}