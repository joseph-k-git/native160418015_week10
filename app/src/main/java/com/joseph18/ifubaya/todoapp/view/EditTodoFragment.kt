package com.joseph18.ifubaya.todoapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.joseph18.ifubaya.todoapp.R
import com.joseph18.ifubaya.todoapp.databinding.FragmentEditTodoBinding
import com.joseph18.ifubaya.todoapp.model.Todo
import com.joseph18.ifubaya.todoapp.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

class EditTodoFragment : Fragment(),
    RadioClickListener, TodoSaveChangesClickListener
{

    private lateinit var viewModel :DetailTodoViewModel
    private lateinit var dataBinding :FragmentEditTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater, R.layout.fragment_edit_todo, container, false)
        //return inflater.inflate(R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid

        /*
        btnCreateTodo.setOnClickListener() {
            val selectedRadioButton = view.findViewById<RadioButton>(radioGroupPirority.checkedRadioButtonId)
            val priorityLevel = Integer.parseInt(selectedRadioButton.tag.toString())

            viewModel.updateTodo(txtTodoTitle.text.toString(), txtTodoNotes.text.toString(), priorityLevel, uuid = uuid)

            Toast.makeText(it.context, "ToDo Edited", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }

         */

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        viewModel.fetchTodo(uuid)

        dataBinding.listener = this
        dataBinding.radiolistener = this

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
            /*
            txtTodoTitle.setText(it.title)
            txtTodoNotes.setText(it.notes)
            checkRadioButtonByPriority(it.priorityLevel)

             */
        })
    }

    override fun onRadioClick(view: View, obj: Todo) {
        obj.priorityLevel = view.tag.toString().toInt()
    }

    override fun onTodoSaveChangesClick(view: View, obj: Todo) {
        viewModel.updateTodo(obj.title, obj.notes, obj.priorityLevel, uuid = obj.uuid)

        Toast.makeText(view.context, "ToDo Edited", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }

    fun checkRadioButtonByPriority(priorityLevel :Int) {
        (radioGroupPirority.children).forEach {
            val radioButton = it as RadioButton
            with (radioButton) {
                this.isChecked = radioButton.tag.toString() == priorityLevel.toString()
            }
        }
    }
}