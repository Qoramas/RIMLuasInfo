package com.shaycock.rimluasinfo.data.remote

import com.shaycock.rimluasinfo.data.model.StopInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestService {

    @GET("xml/get.ashx?action=forecast&encrypt=false")
    suspend fun getLuasForecast(@Query("stop") stop: String) : StopInfo

}