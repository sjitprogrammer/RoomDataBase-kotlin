package com.aodev.roomdatabase

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.aodev.roomdatabase.data.Todo
import com.aodev.roomdatabase.databinding.ActivityNewTodoBinding
import kotlinx.android.synthetic.main.activity_new_todo.*
import java.util.*

class NewTodoActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_todo)
        button_add.setOnClickListener {
            saveTodo()
        }
    }

    fun openDateTimePicker(view: View) {
        val calendar: Calendar = Calendar.getInstance()
        val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
            binding.editTextTime.setText("${hourOfDay}:${minute}")
        }
        val dialog = TimePickerDialog(
            this,
            timeListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        dialog.show()
    }

    private fun saveTodo() {
        val title = binding.editTextTitle.text.trim().toString()
        val time = binding.editTextTime.text.toString()
        val todo = Todo(0,title,time,false)
        val bundle = bundleOf("bundle" to todo)
        val intent  = Intent()
        intent.putExtra("new_todo",bundle)
        setResult(99,intent)
        finish()
    }
}