package com.nightmareinc.communere.userdetail

import com.nightmareinc.communere.database.User

data class UserDetailViewState (

    val user: User,
    val isAdmin: Boolean

)