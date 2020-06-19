package com.nightmareinc.communere.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.nightmareinc.communere.Constant
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.UserDatabaseDao
import com.nightmareinc.communere.util.SingleLiveData

class LoginViewModel(
    val database: UserDatabaseDao) : ViewModel() {

    val navigateToUserList = SingleLiveData.SingleLiveEvent<UserEvent>()

    fun onLoginButtonClick(user: String, password: String) {
        if (user == Constant.ADMIN_USER && password == Constant.ADMIN_PASSWORD) {
            navigateToUserList.value = UserEvent(0, -1L)
        }
    }

    val navigateToSignup = SingleLiveData.SingleLiveEvent<String>()
    fun onSignupButtonClick() {
        navigateToSignup.value = "signup"
    }

}