package com.nightmareinc.communere.repository

import com.nightmareinc.communere.Constant
import com.nightmareinc.communere.Role
import com.nightmareinc.communere.UserAuthInfo
import com.nightmareinc.communere.database.User
import com.nightmareinc.communere.database.UserDatabaseDao

class UserRepository(val database: UserDatabaseDao) {

    suspend fun login(username: String, password: String) : UserAuthInfo {
        if (username == Constant.ADMIN_USER && password == Constant.ADMIN_PASSWORD) {
            return UserAuthInfo(Role.ADMIN, -1)
        }

        val user = database.checkCredential(username, password)
        if (user != null) {
            return UserAuthInfo(Role.USER, user.userId)
        }

        throw AuthenticateFailedException()
    }

    suspend fun signUp(user: User) : UserAuthInfo {
        val userId = database.insert(user)
        return UserAuthInfo(Role.USER, userId)
    }

    suspend fun getUser(userId: Long): User {
        val user = database.get(userId)
        return user
    }

    suspend fun deleteUser() {
        database.clear()
    }

    fun getAllUsers() = database.getAllUsers()

}