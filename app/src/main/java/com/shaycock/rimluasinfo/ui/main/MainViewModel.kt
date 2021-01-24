package com.shaycock.rimluasinfo.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.shaycock.rimluasinfo.api.DataWrapper
import kotlinx.coroutines.launch
import com.shaycock.rimluasinfo.data.model.StopInfo
import com.shaycock.rimluasinfo.repository.LuasRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val luasRepository = LuasRepository.getInstance(application)

    private val stopInfoLiveData: LiveData<DataWrapper<StopInfo>> = liveData(Dispatchers.IO) {
        emitSource(luasRepository.getLuasForecast())
    }

    val stopInfoMedLD = MediatorLiveData<DataWrapper<StopInfo>>()

    init {
        stopInfoMedLD.addSource(stopInfoLiveData) { stopInfoMedLD.value = it }
    }

    fun refreshStopInfo() {
        stopInfoMedLD.value?.let {
            stopInfoMedLD.value!!.data?.let {
                stopInfoMedLD.value!!.data!!.stop = ""
                stopInfoMedLD.postValue(stopInfoMedLD.value)
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            luasRepository.getLuasForecast()
        }
    }
}