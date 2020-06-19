package com.nightmareinc.communere.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.UserEvent
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
    private var user = MutableLiveData<User?>()

    val navigateToUserDetail = SingleLiveData.SingleLiveEvent<UserEvent>()
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