package com.example.currencylist.di.module.app

import com.example.currencylist.common.Constant
import com.example.currencylist.data.remote.ApiService
import com.example.currencylist.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetModule {

    @AppScope
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @AppScope
    @Provides
    fun provideApiService(
        client: OkHttpClient
    ): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}

