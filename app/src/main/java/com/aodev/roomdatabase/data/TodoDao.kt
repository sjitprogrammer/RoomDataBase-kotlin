package com.aodev.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface TodoDao {

    @Query("SELECT * from todo_table ORDER BY todo_id DESC")
    fun getTodo(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()
}