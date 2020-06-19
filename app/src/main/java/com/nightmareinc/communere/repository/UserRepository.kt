package com.nightmareinc.communere.repository

import android.accounts.AuthenticatorException
import com.nightmareinc.communere.Constant
import com.nightmareinc.communere.UserEvent
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.database.UserDatabaseDao

class UserRepository(val database: UserDatabaseDao) {

    suspend fun login(username: String, password: String) : UserEvent {
        if (username == Constant.ADMIN_USER && password == Constant.ADMIN_PASSWORD) {
            return UserEvent(0, -1)
        }

        val user = database.checkCredential(username, password)
        if (user != null) {
            return UserEvent(1, user.userId)
        }

        throw AuthenticateFailedException()
    }

    suspend fun signUp(user: User) : UserEvent {
        val userId = database.insert(user)
        return UserEvent(1, userId)
    }

    suspend fun getUser(userId: Long): User {
        val user = database.get(userId)
        return user
    }

    suspend fun deleteUser() {
        database.clear()
    }

    suspend fun getAllUsers() = database.getAllUsers()

}