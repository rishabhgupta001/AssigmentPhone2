package com.sample.vkoelassign.network

import com.sample.vkoelassign.ui.model.VenueResponeModel
import com.sample.vkoelassign.utility.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.*

interface Api {

    companion object {
        fun create(): Api {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(Api::class.java)
        }
    }


    //https://api.seatgeek.com/2/venues?per_page=10&page=1&client_id=Mzg0OTc0Njl8MTcwMDgxMTg5NC44MDk2NjY5&lat=12.971599&lon=77.594566&range=12mi&q=ub
    @GET("2/venues")
    fun getVenueListResponse(
        @Query("per_page") perPage: String,
        @Query("page") page: String,
        @Query("client_id") clientId: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("range") range: String,
        @Query("q") q: String,
    ): Observable<VenueResponeModel>


}