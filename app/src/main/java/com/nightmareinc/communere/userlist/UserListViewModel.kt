package com.nightmareinc.communere.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.Role
import com.nightmareinc.communere.UserAuthInfo
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.repository.UserRepository
import com.nightmareinc.communere.userdetail.UserDetailViewState
import com.nightmareinc.communere.util.SingleLiveData
import kotlinx.coroutines.*

class UserListViewModel(val userAuthInfo: UserAuthInfo,
                        var userRepository: UserRepository) : ViewModel() {

    private val vieModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + vieModelJob)
    val viewStateLiveData = MutableLiveData<UserDetailViewState>()
    var usersLiveData: LiveData<List<User>> = MutableLiveData()

    init {
            usersLiveData = userRepository.getAllUsers()
    }

    val navigateToUserDetail = SingleLiveData.SingleLiveEvent<UserAuthInfo>()

    fun onUserClicked(id: Long) {
        navigateToUserDetail.value = UserAuthInfo(Role.ADMIN, id)
    }

    fun deleteSelectedUser(id: Long) {
        uiScope.launch {
            userRepository.deleteSelectedUser(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        vieModelJob.cancel()
    }

}