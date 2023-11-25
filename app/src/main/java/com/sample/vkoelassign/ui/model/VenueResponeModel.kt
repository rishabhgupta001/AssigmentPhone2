package com.sample.vkoelassign.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sample.vkoelassign.network.StatusCode

data class VenueResponeModel(
    @SerializedName("venues") var venues: ArrayList<Venues>? = null,
    @SerializedName("meta") var meta: Meta? = null,
    var statusCode: StatusCode = StatusCode.START,
    val error: Int = 0,
    val code: Int = 0,
    var msg: String = "",
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

}
