package com.example.packagerapp.interactors.APIs

import com.example.packagerapp.models.MyPackage
import retrofit2.Callback

interface IRemoteDatabaseInteractor{
    fun getPackages(handleResponse: (packages: List<MyPackage?>?) -> Unit)
    fun addOrUpdatePackage(packageObject: MyPackage, handleResponse: (packageObject: MyPackage?) -> Unit)
    fun getPackagesBasedOnSearch(searchString: String, handleResponse: (packages: List<MyPackage?>?) -> Unit)
    fun deletePackage(packageId: String, handleResponse: (packageObject: MyPackage?) -> Unit)
}