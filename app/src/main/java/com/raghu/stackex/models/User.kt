package com.raghu.stackex.models

import android.os.Parcel
import android.os.Parcelable

data class User(val user_id: Int, val display_name: String, val reputation: Int, val badge_counts: BadgeCounts, val location: String?, val creation_date: Long, val profile_image: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString()!!, parcel.readInt(), parcel.readParcelable<BadgeCounts>(BadgeCounts.javaClass.classLoader)!!, parcel.readString(), parcel.readLong(), parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(user_id)
        parcel.writeString(display_name)
        parcel.writeInt(reputation)
        parcel.writeParcelable(badge_counts, flags)
        parcel.writeString(location)
        parcel.writeLong(creation_date)
        parcel.writeString(profile_image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}

data class BadgeCounts(val gold: Int, val silver: Int, val bronze: Int) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readInt(), parcel.readInt())
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(gold)
        parcel.writeInt(silver)
        parcel.writeInt(bronze)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BadgeCounts> {
        override fun createFromParcel(parcel: Parcel): BadgeCounts {
            return BadgeCounts(parcel)
        }

        override fun newArray(size: Int): Array<BadgeCounts?> {
            return arrayOfNulls(size)
        }
    }
}