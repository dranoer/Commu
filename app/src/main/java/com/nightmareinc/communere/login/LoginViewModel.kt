package com.nightmareinc.communere.login

import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.Role
import com.nightmareinc.communere.UserAuthInfo
import com.nightmareinc.communere.repository.AuthenticateFailedException
import com.nightmareinc.communere.repository.UserRepository
import com.nightmareinc.communere.util.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginViewModel (var userRepository: UserRepository) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val navigateToUserList = SingleLiveData.SingleLiveEvent<UserAuthInfo>()
    val navigateToUserDetail = SingleLiveData.SingleLiveEvent<UserAuthInfo>()

    fun onLoginButtonClick(user: String, password: String) {
        try {
            uiScope.launch {
                val userEvent = userRepository.login(user, password)
                if (userEvent.role == Role.ADMIN) {
                    navigateToUserList.value = userEvent
                } else {
                    navigateToUserDetail.value = userEvent
                }
            }
        } catch (e: AuthenticateFailedException) {
            e.printStackTrace()
        }
    }

    val navigateToSignup = SingleLiveData.SingleLiveEvent<String>()
    fun onSignupButtonClick() {
        navigateToSignup.value = "signup"
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}