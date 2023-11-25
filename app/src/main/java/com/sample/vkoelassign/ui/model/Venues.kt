package com.sample.vkoelassign.ui.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Venues(
    @SerializedName("state") var state: String? = "",
    @SerializedName("name_v2") var nameV2: String? = "",
    @SerializedName("postal_code") var postalCode: String? = "",
    @SerializedName("name") var name: String? = "",
    @SerializedName("links") var links: ArrayList<String>? =null,
    @SerializedName("timezone") var timezone: String? = "",
    @SerializedName("url") var url: String? = "",
    @SerializedName("score") var score: Int? = -1,
    @SerializedName("location") var location: Location? = null,
    @SerializedName("address") var address: String? = "",
    @SerializedName("country") var country: String? = "",
    @SerializedName("has_upcoming_events") var hasUpcomingEvents: Boolean? = false,
    @SerializedName("num_upcoming_events") var numUpcomingEvents: Int? = -1,
    @SerializedName("city") var city: String? = "",
    @SerializedName("slug") var slug: String? = "",
    @SerializedName("extended_address") var extendedAddress: String? = "",
    @SerializedName("stats") var stats: Stats? = null,
    @SerializedName("id") var id: Int? = -1,
    @SerializedName("popularity") var popularity: Int? = -1,
    @SerializedName("access_method") var accessMethod: String? = "",
    @SerializedName("metro_code") var metroCode: Int? = -1,
    @SerializedName("capacity") var capacity: Int? = -1,
    @SerializedName("display_location") var displayLocation: String? = "",
    var imageURL: String? = "",

) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

}
