package com.nightmareinc.communere.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.database.UserDatabaseDao
import kotlinx.coroutines.*

class SignupViewModel(
    val database: UserDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var user = MutableLiveData<User?>()

    // Navigating after Signup --->
    /*private val _navigateToMainScreen = MutableLiveData<Long>()
    val navigateToMainScreen: LiveData<Long>
        get() = _navigateToMainScreen

    fun doneNavigating() {
        _navigateToMainScreen.value = null
    }*/
    // <---

    // Add new user --->
    private suspend fun insert(user: User) {
        withContext(Dispatchers.IO) {
            database.insert(user)
        }
    }

    fun onAddUser(name: String, email: String, password: String) {
        uiScope.launch {
            val newUser = User()
            newUser.fullname = name
            newUser.email = email
            newUser.password = password

            insert(newUser)
        }
    }
    // <---

}