package com.nightmareinc.communere.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.Constant
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.database.UserDatabaseDao
import com.nightmareinc.communere.util.SingleLiveData
import kotlinx.coroutines.*

class SignupViewModel(
    val database: UserDatabaseDao) : ViewModel() {

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

            val id = insert(newUser)
            navigateToUserDetail.value = UserEvent(1, id)
        }
    }

    // Add new user --->
    private suspend fun insert(user: User) = withContext(Dispatchers.IO) {
            return@withContext database.insert(user)
    }


    // <---

}