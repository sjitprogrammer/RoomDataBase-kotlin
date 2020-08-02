package com.aodev.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aodev.roomdatabase.data.Todo

class TodoAdapter() : RecyclerView.Adapter<TodoViewHolder>() {

    var todolList: List<Todo> = listOf<Todo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun getItemCount() = todolList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val current = todolList[position]
        holder.todo_title.text = current.title
        holder.todo_time.text = current.time
        if(current.isComplete){
            holder.todo_complete.isChecked
        }
    }

    fun addAllTodo(list: List<Todo>){
        this.todolList = list
    }

}

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val todo_title: TextView = itemView.findViewById(R.id.textView_title)
    val todo_time: TextView = itemView.findViewById(R.id.textView_time)
    val todo_complete: CheckBox = itemView.findViewById(R.id.checkBox)

}
