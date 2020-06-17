package com.nightmareinc.communere.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nightmareinc.communere.database.UserDatabaseDao

class SignupViewModel(
    val database: UserDatabaseDao,
    application: Application) : AndroidViewModel(application) {
}