package com.aodev.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aodev.roomdatabase.data.Todo
import com.aodev.roomdatabase.data.TodoDao
import com.aodev.roomdatabase.data.TodoDatabase
import com.aodev.roomdatabase.data.TodoRepository
import com.aodev.roomdatabase.databinding.ActivityMainBinding
const val ADD_TODO_CODE = 99
const val REQUEST_ADD_TODO = "REQUEST_ADD_TODO"
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var todoRepository:TodoRepository
    lateinit var todoAdapter: TodoAdapter
    lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        initView()
    }

    private fun initView() {
        val todoDao:TodoDao = TodoDatabase.getDatabase(this).todoDao()
        todoRepository = TodoRepository(todoDao)
        val viewModelFactory: TodoViewModelFactory = TodoViewModelFactory(todoRepository)
        todoViewModel = ViewModelProvider(this,viewModelFactory).get(TodoViewModel::class.java)
        val recyclerView:RecyclerView = findViewById(R.id.recyclerview_todo)
        todoAdapter = TodoAdapter()
        binding.recyclerviewTodo.adapter = todoAdapter
        binding.recyclerviewTodo.layoutManager = LinearLayoutManager(this)
        binding.floatingActionButton2.setOnClickListener {
            val intent = Intent(this,NewTodoActivity::class.java)
            startActivityForResult(intent, ADD_TODO_CODE)
        }
        showTodo()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode== ADD_TODO_CODE){
            val bundle = data!!.getBundleExtra("new_todo")
            val todo = bundle.getParcelable<Todo>("bundle")
            Toast.makeText(this,""+todo.toString(),Toast.LENGTH_LONG).show()
            todoViewModel.insert(todo!!)
        }
    }

    private fun showTodo() {
        todoViewModel.allTodo.observe(this, Observer {
            todoAdapter.addAllTodo(it)
            binding.recyclerviewTodo.adapter = todoAdapter
        })
    }
}