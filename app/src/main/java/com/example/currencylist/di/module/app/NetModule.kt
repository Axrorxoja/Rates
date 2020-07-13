package com.example.currencylist.di.module.app

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.currencylist.common.Constant
import com.example.currencylist.data.remote.ApiService
import com.example.currencylist.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetModule {

    @AppScope
    @Provides
    fun provideApiService(context: Context): ApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .addInterceptor(HttpLoggingInterceptor().also { it.level=HttpLoggingInterceptor.Level.BODY })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}

