package com.example.packagerapp.interactors.APIs

import com.example.packagerapp.interactors.APIs.IRemoteDatabaseAPI
import com.example.packagerapp.interactors.APIs.IRemoteDatabaseInteractor
import com.example.packagerapp.models.MyPackage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteDatabaseInteractor
@Inject constructor(private var remoteDatabaseAPI: IRemoteDatabaseAPI)
    : IRemoteDatabaseInteractor {

    override fun getPackages(handleResponse: (packages: List<MyPackage?>?) -> Unit){
        var call = remoteDatabaseAPI.packagerGetAllPackages()

        call?.enqueue(object: Callback<List<MyPackage?>?>{
            override fun onFailure(call: Call<List<MyPackage?>?>, t: Throwable) {
                var packages = listOf<MyPackage>()
                handleResponse(packages)
            }

            override fun onResponse(call: Call<List<MyPackage?>?>, response: Response<List<MyPackage?>?>) {
                var packages = response.body()

                if (!checkPackagesValidity(packages))
                {
                    packages = listOf<MyPackage>()
                }

                handleResponse(packages)
            }
        })
    }

    override fun addOrUpdatePackage(packageObject: MyPackage, handleResponse: (packageObject: MyPackage?) -> Unit) {
        var call = remoteDatabaseAPI.packagerPutOrUpdatePackage(packageObject)

        call?.enqueue(object: Callback<MyPackage?>{
            override fun onFailure(call: Call<MyPackage?>, t: Throwable) {
                throw NotImplementedError()
            }

            override fun onResponse(call: Call<MyPackage?>, response: Response<MyPackage?>) {
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

    override fun getPackagesBasedOnSearch(searchString: String, handleResponse: (packages: List<MyPackage?>?) -> Unit){
        var call = remoteDatabaseAPI.packagerGetSpecificPackages(searchString)

        call?.enqueue(object: Callback<List<MyPackage?>?>{
            override fun onFailure(call: Call<List<MyPackage?>?>, t: Throwable) {
                var packages = listOf<MyPackage>()
                handleResponse(packages)
            }

            override fun onResponse(call: Call<List<MyPackage?>?>, response: Response<List<MyPackage?>?>) {
                var packages = response.body()

                if (!checkPackagesValidity(packages))
                {
                    packages = listOf<MyPackage>()
                }

                handleResponse(packages)
            }
        })
    }

    override fun deletePackage(packageId: String, handleResponse: (packageObject: MyPackage?) -> Unit) {
        var call = remoteDatabaseAPI.packagerDeletePackage(packageId)

        call?.enqueue(object: Callback<MyPackage?>{
            override fun onFailure(call: Call<MyPackage?>, t: Throwable) {
                handleResponse(null)
            }

            override fun onResponse(call: Call<MyPackage?>, response: Response<MyPackage?>) {
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

    private fun checkPackagesValidity(packages : List<MyPackage?>?) : Boolean
    {
        if(packages == null) return false

        for (packageItem in packages!!) {
            if (!checkPackageValidity(packageItem)) return false;
        }

        return true
    }

    private fun checkPackageValidity(packageObject: MyPackage?) : Boolean
    {
        //TODO: Implement properly
        return true
    }
}