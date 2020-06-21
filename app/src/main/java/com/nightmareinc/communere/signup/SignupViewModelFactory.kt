package com.nightmareinc.communere.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nightmareinc.communere.repository.UserRepository

class SignupViewModelFactory (var userRepository: UserRepository): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}