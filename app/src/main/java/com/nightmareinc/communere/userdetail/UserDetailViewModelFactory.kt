package com.nightmareinc.communere.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nightmareinc.communere.UserAuthInfo
import com.nightmareinc.communere.repository.UserRepository
import java.lang.IllegalArgumentException

class UserDetailViewModelFactory (val userAuthInfo: UserAuthInfo,
                                  var userRepository: UserRepository) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            return UserDetailViewModel(userAuthInfo, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}