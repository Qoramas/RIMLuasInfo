package com.shaycock.rimluasinfo.api.interceptor

import android.content.Context
import com.shaycock.rimluasinfo.BuildConfig
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.lang.Exception

/**
 * Class designed to override select rest calls and return a given xml/json file from the assets
 * Very useful for quickly manually testing scenerios and new/broken apis
 */
class TestInterceptor(val context: Context) : Interceptor {

    private fun getStringFromAsset(filename: String) : String {
        return try {
            var inputStream = context.assets.open(filename)
            inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            ""
        }
    }

    private fun getSuccess(chain: Interceptor.Chain, str: String, mediaType: String) : Response {
        return Response.Builder()
            .code(200)
            .message(str)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(ResponseBody.create(mediaType.toMediaTypeOrNull(), str.toByteArray()))
            .addHeader("content-type", mediaType)
            .build()
    }

    private fun getFailure(chain: Interceptor.Chain, str: String, mediaType: String) : Response {
        return Response.Builder()
            .code(500)
            .message(str)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(ResponseBody.create(mediaType.toMediaTypeOrNull(), str.toByteArray()))
            .addHeader("content-type", mediaType)
            .build()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var response : Response? = null
        if (BuildConfig.DEBUG) {
            var url = chain.request().url.toUri().path
            if (url.contains("xml")) {
//                response = getSuccess(chain, getStringFromAsset("luas_bad_data.xml"), "application/xml")
//                response = getSuccess(chain, getStringFromAsset("luas_full_due.xml"), "application/xml")
//                response = getSuccess(chain, getStringFromAsset("luas_no_service.xml"), "application/xml")
//                response = getFailure(chain, getStringFromAsset("luas_no_service.xml"), "application/xml")
            }
        }

        if(response == null) {
            response = chain.proceed(chain.request())
        }

        return response
    }

}