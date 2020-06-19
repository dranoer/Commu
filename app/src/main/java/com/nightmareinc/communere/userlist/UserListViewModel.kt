package com.nightmareinc.communere.userlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.database.UserDatabaseDao
import com.nightmareinc.communere.util.SingleLiveData
import kotlinx.coroutines.*

class UserListViewModel(
    val userEvent: UserEvent,
    val database: UserDatabaseDao) : ViewModel() {

    private val vieModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + vieModelJob)

    lateinit var userList: LiveData<List<User>>

    private suspend fun getAllUsers(){
         withContext(Dispatchers.IO) {
            database.getAllUsers()
        }
    }

    ///
//    private val _navigateToDetail = MutableLiveData<Long>()
    val navigateToUserDetail = SingleLiveData.SingleLiveEvent<UserEvent>()

    fun onUserClicked(id: Long) {
        navigateToUserDetail.value = UserEvent(0, id)
    }

}