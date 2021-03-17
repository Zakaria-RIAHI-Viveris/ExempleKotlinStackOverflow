package com.viveris.exemplekotlinstackoverflow.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class BadgeCounts(
    var bronze: Int?,
    var silver: Int? = 0,
    var gold: Int? = 0
) : Parcelable {

    override fun toString(): String {
        return "Gold : $gold Silver : $silver Bronze : $bronze"
    }

}