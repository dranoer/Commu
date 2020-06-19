package com.nightmareinc.communere.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.repository.UserRepository
import com.nightmareinc.communere.userdetail.UserDetailViewState
import com.nightmareinc.communere.util.SingleLiveData
import kotlinx.coroutines.*

class UserListViewModel(val userEvent: UserEvent,
                        var userRepository: UserRepository) : ViewModel() {

    private val vieModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + vieModelJob)
    val viewStateLiveData = MutableLiveData<UserDetailViewState>()
    var usersLiveData: LiveData<List<User>> = MutableLiveData()
//            = userRepository.getAllUsers()

//    lateinit var userList: LiveData<List<User>>

    init {
        uiScope.launch {
//            var userList = userRepository.getAllUsers()
            usersLiveData = userRepository.getAllUsers()

//            val state = UserDetailViewState(user, userEvent.role == 1)
//            viewStateLiveData.value = state
        }
    }

    /*private suspend fun getAllUsers(){
         withContext(Dispatchers.IO) {
            database.getAllUsers()
        }
    }*/

    ///
//    private val _navigateToDetail = MutableLiveData<Long>()
    val navigateToUserDetail = SingleLiveData.SingleLiveEvent<UserEvent>()

    fun onUserClicked(id: Long) {
        navigateToUserDetail.value = UserEvent(0, id)
    }

}