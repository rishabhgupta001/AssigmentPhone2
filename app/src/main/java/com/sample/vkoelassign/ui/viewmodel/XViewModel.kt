package com.sample.vkoelassign.ui.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sample.vkoelassign.network.Api
import com.sample.vkoelassign.network.StatusCode
import com.sample.vkoelassign.ui.model.VenueResponeModel
import com.sample.vkoelassign.utility.Constants
import com.sample.vkoelassign.utility.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class XViewModel(application: Application) : AndroidViewModel(application) {
    var venueListresponseData: MutableLiveData<VenueResponeModel> = MutableLiveData()

    // get list of notification
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("CheckResult")
    fun getVenueList() {
        val responseModel = VenueResponeModel()
        if (!Utils.isInternetAvailable(getApplication())) {
            responseModel.statusCode = StatusCode.NETWORK_ERROR
            venueListresponseData.value = responseModel
        } else {
            val observable = Api.create()
                .getVenueListResponse(
                    Constants.per_page,
                    Constants.page,
                    Constants.client_id,
                    Constants.lat,
                    Constants.lon,
                    Constants.range,
                    Constants.q
                )
            observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    responseModel.statusCode = StatusCode.START
                    venueListresponseData.postValue(responseModel)
                }
                .subscribe({ success ->
                    Log.d("vvvv", "success ${success.venues?.size}")
                    success.statusCode = StatusCode.SUCCESS
                    venueListresponseData.postValue(success)
                }, { it ->
                    Log.d("vvvv", "it ${it.message}")
                    responseModel.msg = it.localizedMessage!!
                    responseModel.statusCode = StatusCode.ERROR
                    venueListresponseData.postValue(responseModel)
                }, {})
        }
    }


}