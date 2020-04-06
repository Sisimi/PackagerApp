package com.example.packagerapp.di

import com.example.packagerapp.interactors.APIs.IRemoteDatabaseAPI
import com.example.packagerapp.interactors.RemoteDatabaseInteractor
import com.example.packagerapp.interactors.IRemoteDatabaseInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton

@Module
class RemoteDatabaseInteractorModule {

    @Provides
    fun provideRemoteDatabaseAPI() : IRemoteDatabaseAPI{


        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain->
                val original = chain.request()

                val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }
            .build()


        val remoteDatabaseAPIInstance: IRemoteDatabaseAPI by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://192.168.0.15:5001/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(IRemoteDatabaseAPI::class.java)
        }

        return remoteDatabaseAPIInstance
    }

    @Provides
    fun provideRemoteDatabaseInteractor(remoteDatabaseAPI : IRemoteDatabaseAPI) : IRemoteDatabaseInteractor
    {
        return RemoteDatabaseInteractor(remoteDatabaseAPI)
    }
}