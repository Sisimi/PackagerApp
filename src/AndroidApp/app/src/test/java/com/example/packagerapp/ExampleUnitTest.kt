package com.example.packagerapp

import android.util.Log
import com.example.packagerapp.interactors.APIs.IRemoteDatabaseAPI
import com.example.packagerapp.interactors.APIs.IRemoteDatabaseInteractor
import com.example.packagerapp.interactors.APIs.RemoteDatabaseInteractor
import com.example.packagerapp.models.MyPackage

import okhttp3.OkHttpClient
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var remoteDatabaseInteractor: IRemoteDatabaseInteractor

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain->
                val original = chain.request()

                val request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build()

                chain.proceed(request)
            }
            //.sslSocketFactory(sslContextFactory, trustAllCerts[0] as X509TrustManager)
            /* .certificatePinner(CertificatePinner.Builder()
                 .add("packagerwebapi.com","sha256/UY/xad0shlsaXkupqh4hHfQd1dZxvtrSV0GNMsb2quE=")
                 .build())*/
            .build()


        val remoteDatabaseAPIInstance: IRemoteDatabaseAPI by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.15:5000/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(IRemoteDatabaseAPI::class.java)
        }

        remoteDatabaseInteractor =
            RemoteDatabaseInteractor(
                remoteDatabaseAPIInstance
            )
    }


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun PackagerAPITest() {
        /*
        remoteDatabaseInteractor.getPackages(fun(packages:List<MyPackage?>?){
            Log.println(10,"Hello","Hello!")
        })

         */
    }





}
