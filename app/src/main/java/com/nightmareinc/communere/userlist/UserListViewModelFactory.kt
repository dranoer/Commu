package com.nightmareinc.communere.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.UserDatabaseDao
import com.nightmareinc.communere.repository.UserRepository
import java.lang.IllegalArgumentException

class UserListViewModelFactory (val userEvent: UserEvent,
                                var userRepository: UserRepository) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(userEvent, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}