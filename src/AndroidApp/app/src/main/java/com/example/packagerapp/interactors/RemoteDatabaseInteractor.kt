package com.example.packagerapp.interactors

import android.util.Log
import com.example.packagerapp.interactors.APIs.IRemoteDatabaseAPI
import com.example.packagerapp.models.Package
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDatabaseInteractor
@Inject constructor(private var remoteDatabaseAPI: IRemoteDatabaseAPI)
    : IRemoteDatabaseInteractor {



    override fun getPackages() : List<Package?>? {
        var call = remoteDatabaseAPI.packagerGetAllPackages()
        var result : List<Package?>? = null



        call?.enqueue(object: Callback<List<Package?>?>{
            override fun onFailure(call: Call<List<Package?>?>, t: Throwable) {
                result = null
                Log.d("Network", t.message)
            }

            override fun onResponse(call: Call<List<Package?>?>, response: Response<List<Package?>?>) {
                result = response.body()
                Log.d("Network", "Success")
                for (packageObject in response.body()!!)
                {
                    Log.d("Response", packageObject!!.packageName)
                }
            }
        })


        return result
    }

    override fun putPackage() {
        throw NotImplementedError()
    }

    override fun deletePackage() {
        throw NotImplementedError()
    }

}