package com.nightmareinc.communere.userlist

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.nightmareinc.communere.database.User

@BindingAdapter("userDelete")
fun ImageView.deleteUser(item: User?) {
    item.let {
        Log.i("userDelete", "touched")
    }
}