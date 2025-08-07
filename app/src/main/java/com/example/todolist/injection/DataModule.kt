package com.example.todolist.injection

import android.app.Application
import androidx.room.Room
import com.example.todolist.data.TodoDao
import com.example.todolist.data.TodoDataBase
import com.example.todolist.data.TodoRepository
import com.example.todolist.data.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideTodoDataBase(app: Application): TodoDataBase {
        return Room.databaseBuilder(
            app.applicationContext,
            TodoDataBase::class.java,
            "todo-app"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(db: TodoDataBase): TodoDao {
        return db.todoDao
    }

    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(dao)
    }
}