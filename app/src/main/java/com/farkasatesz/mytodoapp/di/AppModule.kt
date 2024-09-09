package com.farkasatesz.mytodoapp.di

import androidx.room.Room
import com.farkasatesz.mytodoapp.data.TodoDatabase
import com.farkasatesz.mytodoapp.repositories.TodoRepository
import com.farkasatesz.mytodoapp.screens.TodoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            TodoDatabase::class.java,
            "todo_db"
        ).fallbackToDestructiveMigration().build()
    }

    single {
        get<TodoDatabase>().todoDao()
    }

    single { TodoRepository(get()) }

    viewModel { TodoViewModel(get()) }

}