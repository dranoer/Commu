package com.nightmareinc.communere.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.nightmareinc.communere.Constant
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.UserDatabaseDao
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

    val navigateToUserList = SingleLiveData.SingleLiveEvent<UserEvent>()
    val navigateToUserDetail = SingleLiveData.SingleLiveEvent<UserEvent>()

    fun onLoginButtonClick(user: String, password: String) {
        try {
            uiScope.launch {
                val userEvent = userRepository.login(user, password)
                if (userEvent.role == 0) {
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