package com.shaycock.rimluasinfo.api
import android.content.Context
import com.shaycock.rimluasinfo.api.interceptor.TestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


object RetrofitInst {
    private const val BASE_URL = "https://luasforecasts.rpa.ie"

    private var INSTANCE: Retrofit? = null

    fun getInstance(context: Context): Retrofit = INSTANCE ?: kotlin.run {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            //Uncomment the following line to implement the testInterceptor
//            .addInterceptor(TestInterceptor(context))
            .build()

        Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(
                        SimpleXmlConverterFactory.createNonStrict(
                                Persister(AnnotationStrategy())
                        ))
                .build()
    }
}
