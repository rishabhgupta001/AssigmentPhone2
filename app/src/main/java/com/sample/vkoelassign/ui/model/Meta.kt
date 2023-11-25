package com.sample.vkoelassign.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Meta(

    @SerializedName("total") var total: Int? = -1,
    @SerializedName("took") var took: Int? = -1,
    @SerializedName("page") var page: Int? = -1,
    @SerializedName("per_page") var perPage: Int? = -1,
    @SerializedName("geolocation") var geolocation: Geolocation? = null
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

}
