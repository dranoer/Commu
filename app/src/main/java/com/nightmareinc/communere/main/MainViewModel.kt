package com.nightmareinc.communere.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nightmareinc.communere.database.UserDatabaseDao

class MainViewModel(
    val database: UserDatabaseDao,
    application: Application) : AndroidViewModel(application) {

}