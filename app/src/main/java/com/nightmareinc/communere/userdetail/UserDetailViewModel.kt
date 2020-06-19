package com.nightmareinc.communere.userdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.Role
import com.nightmareinc.communere.UserAuthInfo
import com.nightmareinc.communere.repository.UserRepository
import com.nightmareinc.communere.util.SingleLiveData
import kotlinx.coroutines.*

class UserDetailViewModel(val userAuthInfo: UserAuthInfo,
                          var userRepository: UserRepository) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val viewStateLiveData = MutableLiveData<UserDetailViewState>()

    init {
        uiScope.launch {
//            val user = getUser()
            val user = userRepository.getUser(userAuthInfo.id)
            val state = UserDetailViewState(user, userAuthInfo.role == Role.ADMIN)
            viewStateLiveData.value = state
        }
    }

    // Get logged user data --->
    /*private suspend fun getUser(): User {
        return withContext(Dispatchers.IO) {
            database.get(userEvent.id)
        }
    }*/

    // Delete user
    val navigateToSignup = SingleLiveData.SingleLiveEvent<String>()
    /*private suspend fun delete() {
        return withContext(Dispatchers.IO) {
            database.clear()
        }
    }*/

    fun deleteUser() {
        uiScope.launch {
//            delete()
            userRepository.deleteUser()
            navigateToSignup.value = "signup"
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}