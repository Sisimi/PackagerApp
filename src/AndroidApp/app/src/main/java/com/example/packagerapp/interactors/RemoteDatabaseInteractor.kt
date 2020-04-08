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

    override fun getPackages(handleResponse: (packages: List<Package?>?) -> Unit){
        var call = remoteDatabaseAPI.packagerGetAllPackages()

        call?.enqueue(object: Callback<List<Package?>?>{
            override fun onFailure(call: Call<List<Package?>?>, t: Throwable) {
                var packages = listOf<Package>()
                handleResponse(packages)
            }

            override fun onResponse(call: Call<List<Package?>?>, response: Response<List<Package?>?>) {
                var packages = response.body()

                if (!checkPackagesValidity(packages))
                {
                    packages = listOf<Package>()
                }

                handleResponse(packages)
            }
        })
    }

    override fun addOrUpdatePackage(packageObject: Package, handleResponse: (packageObject: Package?) -> Unit) {
        var call = remoteDatabaseAPI.packagerPutOrUpdatePackage(packageObject)

        call?.enqueue(object: Callback<Package?>{
            override fun onFailure(call: Call<Package?>, t: Throwable) {

            }

            override fun onResponse(call: Call<Package?>, response: Response<Package?>) {
                var packageObject = response.body()

                if(!checkPackageValidity(packageObject))
                {
                    handleResponse(null)
                    return
                }

                handleResponse(packageObject)
            }
        })
    }

    override fun getPackagesBasedOnSearch(searchString: String, handleResponse: (packages: List<Package?>?) -> Unit){
        var call = remoteDatabaseAPI.packagerGetSpecificPackages(searchString)

        call?.enqueue(object: Callback<List<Package?>?>{
            override fun onFailure(call: Call<List<Package?>?>, t: Throwable) {
                var packages = listOf<Package>()
                handleResponse(packages)
            }

            override fun onResponse(call: Call<List<Package?>?>, response: Response<List<Package?>?>) {
                var packages = response.body()

                if (!checkPackagesValidity(packages))
                {
                    packages = listOf<Package>()
                }

                handleResponse(packages)
            }
        })
    }

    override fun deletePackage(packageId: String, handleResponse: (packageObject: Package?) -> Unit) {
        var call = remoteDatabaseAPI.packagerDeletePackage(packageId)

        call?.enqueue(object: Callback<Package?>{
            override fun onFailure(call: Call<Package?>, t: Throwable) {
                handleResponse(null)
            }

            override fun onResponse(call: Call<Package?>, response: Response<Package?>) {
                var deletedPackage = response.body()

                if(!checkPackageValidity(deletedPackage))
                {
                    handleResponse(null)
                    return
                }

                handleResponse(deletedPackage)
            }
        })
    }

    private fun checkPackagesValidity(packages : List<Package?>?) : Boolean
    {
        if(packages == null) return false

        for (packageItem in packages!!) {
            if (!checkPackageValidity(packageItem)) return false;
        }

        return true
    }

    private fun checkPackageValidity(packageObject: Package?) : Boolean
    {
        //TODO: Implement properly
        return true
    }
}