package com.sample.vkoelassign.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Geolocation(
    @SerializedName("lat") var lat: String? = "",
    @SerializedName("lon") var lon: String? = "",
    @SerializedName("city") var city: String? = "",
    @SerializedName("state") var state: String? = "",
    @SerializedName("country") var country: String? = "",
    @SerializedName("postal_code") var postalCode: String? = "",
    @SerializedName("display_name") var displayName: String? = "",
    @SerializedName("metro_code") var metroCode: String? = "",
    @SerializedName("range") var range: String? = ""
): Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

}
