package com.nightmareinc.communere.userdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.database.UserDatabaseDao
import kotlinx.coroutines.*

class UserDetailViewModel(
    val database: UserDatabaseDao) : ViewModel() {

    private val vieModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + vieModelJob)

    lateinit var userList: LiveData<List<User>>

    // Get logged user data --->
    /*private suspend fun getUser(): User {
        return withContext(Dispatchers.IO) {
            database.get(userId)
        }
    }*/

    private suspend fun getAllUsers(){
         withContext(Dispatchers.IO) {
            database.getAllUsers()
        }
    }

    fun checkRole(role: Boolean) {
        uiScope.launch {
            if (role == true) {
                Log.i("nightmare", "admin login")
//                val users = getAllUsers()
                /*userList = Transformations.map(getAllUsers()) {
                    user ->

                }*/

            } else {
//                getUser()
            }
        }
    }
    // <---

}