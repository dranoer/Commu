package com.nightmareinc.communere.userdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.Role
import com.nightmareinc.communere.UserAuthInfo
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.repository.UserRepository
import com.nightmareinc.communere.util.SingleLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserDetailViewModel(val userAuthInfo: UserAuthInfo,
                          var userRepository: UserRepository) : ViewModel() {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val viewStateLiveData = MutableLiveData<UserDetailViewState>()
    lateinit var currentUser: User

    init {
        uiScope.launch {
            val user = userRepository.getUser(userAuthInfo.id)
            currentUser = user
            val state = UserDetailViewState(user, userAuthInfo.role == Role.ADMIN)
            viewStateLiveData.value = state
        }
    }

    // Delete user
    val navigateToSignup = SingleLiveData.SingleLiveEvent<String>()

    fun deleteUser() {
        uiScope.launch {
            userRepository.deleteUser()
            navigateToSignup.value = "signup"
        }
    }

    // Update user
    fun updateUser() {
        uiScope.launch {
            userRepository.updateUser(currentUser)
            navigateToSignup.value = "signupUpdate"
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}