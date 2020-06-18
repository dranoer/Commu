package com.nightmareinc.communere.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.database.UserDatabaseDao
import kotlinx.coroutines.*

class MainViewModel(
    val role: Boolean,
    val database: UserDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private val vieModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + vieModelJob)

    // Get logged user data --->
    /*private suspend fun getUser(): User {
        return withContext(Dispatchers.IO) {
            database.get(userId)
        }
    }*/

    fun checkRole(role: Boolean) {
        uiScope.launch {
            if (role == true) {
                Log.i("nightmare", "admin login")
            } else {
//                getUser()
            }
        }
    }
    // <---

}