package com.nightmareinc.communere.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nightmareinc.communere.database.UserDatabaseDao

class LoginViewModel(
    val database: UserDatabaseDao,
    application: Application) : AndroidViewModel(application) {

}