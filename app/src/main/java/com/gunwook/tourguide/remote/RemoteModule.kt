package com.gunwook.tourguide.remote

import android.content.Context
import androidx.annotation.NonNull
import com.gunwook.tourguide.BuildConfig
import com.gunwook.tourguide.utils.CodeUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RemoteModule : KoinComponent {

    private val context : Context by inject()

    @Throws(IOException::class)
    private fun provideRetrofit(@NonNull okHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(CodeUtils.Network.TOUR_API)
            .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                Persister(AnnotationStrategy())
            ))
            .build()
    }
    @Throws(IOException::class)
    private fun provideOkHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE // logging

        return OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }
    @Throws(IOException::class)
    private fun getApiCallInterface(retrofit : Retrofit) : RemoteApi {
        return retrofit.create(RemoteApi::class.java)
    }

    @Throws(IOException::class)
    fun getHelper() : RemoteHelper {
        return RemoteHelper(getApiCallInterface(provideRetrofit(provideOkHttpClient())))
    }


}