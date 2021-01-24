package com.shaycock.rimluasinfo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shaycock.rimluasinfo.api.DataWrapper
import com.shaycock.rimluasinfo.api.ErrorObject
import com.shaycock.rimluasinfo.api.RetrofitInst
import com.shaycock.rimluasinfo.data.model.Direction
import com.shaycock.rimluasinfo.data.model.StopInfo
import com.shaycock.rimluasinfo.data.remote.RestService
import org.joda.time.DateTime
import java.lang.Exception
import java.text.SimpleDateFormat

class LuasRepository (application: Application) {
    private val restService = RetrofitInst.getInstance(application.applicationContext).create(RestService::class.java)
    private val stopInfoLiveData = MutableLiveData<DataWrapper<StopInfo>>()

    suspend fun getLuasForecast() : LiveData<DataWrapper<StopInfo>> {
        val now = DateTime()
        var selectedStop : String
        selectedStop = if (now.hourOfDay().get() >= 12) {
            StopInfo.STOP_STI
        } else {
            StopInfo.STOP_MAR
        }

        try {
            val stopInfo = restService.getLuasForecast(selectedStop)

            stopInfo.selectedDirection = if (now.hourOfDay().get() >= 12) {
                Direction.DIRECTION_INBOUND
            } else {
                Direction.DIRECTION_OUTBOUND
            }

            stopInfoLiveData.postValue(DataWrapper(stopInfo))
        } catch (exception: Exception) {
            stopInfoLiveData.postValue(DataWrapper(null, ErrorObject(exception)))
            exception.printStackTrace()
        }

        return stopInfoLiveData
    }

    companion object {
        private var INSTANCE: LuasRepository? = null

        fun getInstance(application: Application): LuasRepository = INSTANCE ?: kotlin.run {
            INSTANCE = LuasRepository(application)
            INSTANCE!!
        }
    }
}