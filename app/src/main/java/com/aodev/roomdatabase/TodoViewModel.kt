package com.aodev.roomdatabase

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aodev.roomdatabase.data.Todo
import com.aodev.roomdatabase.data.TodoDatabase
import com.aodev.roomdatabase.data.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(val repository: TodoRepository) : ViewModel() {
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allTodo: LiveData<List<Todo>>

    init {
        allTodo = repository.allTodos
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(todo)
    }

    fun update(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(todo)
    }

    fun delete(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(todo)
    }

    fun deleteAll(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}