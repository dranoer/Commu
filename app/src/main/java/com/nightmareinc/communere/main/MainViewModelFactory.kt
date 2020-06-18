package com.nightmareinc.communere.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nightmareinc.communere.database.UserDatabaseDao
import java.lang.IllegalArgumentException

class MainViewModelFactory (
    private val role: Boolean,
    private val dataSource: UserDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(role, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}