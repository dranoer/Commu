package com.nightmareinc.communere.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nightmareinc.communere.UserAuthInfo
import com.nightmareinc.communere.repository.UserRepository
import java.lang.IllegalArgumentException

class UserListViewModelFactory (val userAuthInfo: UserAuthInfo,
                                var userRepository: UserRepository) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(userAuthInfo, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}