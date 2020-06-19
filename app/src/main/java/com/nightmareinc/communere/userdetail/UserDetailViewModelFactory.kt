package com.nightmareinc.communere.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.UserDatabaseDao
import java.lang.IllegalArgumentException

class UserDetailViewModelFactory (
    val userEvent: UserEvent,
    private val dataSource: UserDatabaseDao) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}