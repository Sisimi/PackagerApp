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



    override fun getPackages(f: (packages: List<Package?>?) -> Unit){
        var call = remoteDatabaseAPI.packagerGetAllPackages()

        call?.enqueue(object: Callback<List<Package?>?>{
            override fun onFailure(call: Call<List<Package?>?>, t: Throwable) {
                Log.d("Network Error", t.message)
            }

            override fun onResponse(call: Call<List<Package?>?>, response: Response<List<Package?>?>) {
                var packages = response.body()

                if (CheckPackages(packages))
                {
                    Log.d("Network", "Success")
                    for (packageObject in response.body()!!)
                    {
                        Log.d("Response", packageObject!!.packageName)
                    }

                    f(packages)
                }
                else
                {
                    f(null)
                }
            }
        })
    }

    override fun putPackage(packageObject: Package) : Package? {
        var call = remoteDatabaseAPI.packagerAddPackage(packageObject)

        var packageResult = call?.execute()?.body()

        if(CheckPackageValidity(packageResult)) return packageResult

        return null
    }

    override fun getPackagesBasedOnSearch(searchString: String) : List<Package?>? {
        var call = remoteDatabaseAPI.packagerGetSpecificPackages(searchString)

        var packages = call?.execute()?.body()

        if(CheckPackages(packages)) return packages

        return null
    }

    override fun deletePackage() {
        throw NotImplementedError()
    }

    private fun CheckPackages(packages : List<Package?>?) : Boolean
    {
        if(packages == null) return false

        for (packageItem in packages!!) {
            if (!CheckPackageValidity(packageItem)) return false;
        }

        return true
    }

    private fun CheckPackageValidity(packageObject: Package?) : Boolean
    {
        //TODO: Implement properly
        return true
    }
}