package com.nightmareinc.communere.userdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.database.UserDatabaseDao
import kotlinx.coroutines.*

class UserDetailViewModel(
    val userEvent: UserEvent,
    val database: UserDatabaseDao) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val viewStateLiveData = MutableLiveData<UserDetailViewState>()

    init {
        uiScope.launch {
            val user = getUser()
            val state = UserDetailViewState(user, userEvent.role == 0)
            viewStateLiveData.value = state
        }
    }

    // Get logged user data --->
    private suspend fun getUser(): User {
        return withContext(Dispatchers.IO) {
            database.get(userEvent.id)
        }
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}