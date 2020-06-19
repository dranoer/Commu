package com.nightmareinc.communere.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.UserDatabaseDao
import java.lang.IllegalArgumentException

class UserListViewModelFactory (
    val userEvent: UserEvent,
    private val dataSource: UserDatabaseDao) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(userEvent, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}