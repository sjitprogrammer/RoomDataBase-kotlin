package com.aodev.roomdatabase.data

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allTodos: LiveData<List<Todo>> = todoDao.getTodo()

    suspend fun insert(todo: Todo) {
        todoDao.insertTodo(todo)
    }
    suspend fun update(todo: Todo) {
        todoDao.updateTodo(todo)
    }
    suspend fun delete(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
    suspend fun deleteAll() {
        todoDao.deleteAll()
    }
}