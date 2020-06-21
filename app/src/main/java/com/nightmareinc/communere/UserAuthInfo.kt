package com.nightmareinc.communere

import android.os.Parcel
import android.os.Parcelable

data class UserAuthInfo (
    val role: Role,
    val id: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("role"),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserAuthInfo> {
        override fun createFromParcel(parcel: Parcel): UserAuthInfo {
            return UserAuthInfo(parcel)
        }

        override fun newArray(size: Int): Array<UserAuthInfo?> {
            return arrayOfNulls(size)
        }
    }
}