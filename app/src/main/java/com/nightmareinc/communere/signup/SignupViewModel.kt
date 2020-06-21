package com.nightmareinc.communere.signup

import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.UserAuthInfo
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.repository.UserRepository
import com.nightmareinc.communere.util.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignupViewModel (var userRepository: UserRepository) : ViewModel() {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val navigateToUserDetail = SingleLiveData.SingleLiveEvent<UserAuthInfo>()
    fun onSignupButtonClick(name: String, email: String, password: String) {
        uiScope.launch {
            val newUser = User()
            newUser.fullname = name
            newUser.email = email
            newUser.password = password

            val userEvent = userRepository.signUp(newUser)
            navigateToUserDetail.value = userEvent
        }
    }

}