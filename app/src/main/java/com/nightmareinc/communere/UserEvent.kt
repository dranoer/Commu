package com.nightmareinc.communere

import android.os.Parcel
import android.os.Parcelable

data class UserEvent (
    val role: Int,
    val id: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(role)
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserEvent> {
        override fun createFromParcel(parcel: Parcel): UserEvent {
            return UserEvent(parcel)
        }

        override fun newArray(size: Int): Array<UserEvent?> {
            return arrayOfNulls(size)
        }
    }
}