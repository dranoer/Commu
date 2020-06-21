package com.nightmareinc.communere.repository

import android.content.Context
import android.util.Log
import com.nightmareinc.communere.util.toast

class AuthenticateFailedException : Throwable() {

    fun userNotExistError(context: Context) {
        context.toast("User not exist.")
    }

    fun error() {
        Log.i("userError", "not exist")
    }

}
