package com.example.packagerapp.interactors

import com.example.packagerapp.models.Package
import retrofit2.Callback

interface IRemoteDatabaseInteractor{
    fun getPackages(handleResponse: (packages: List<Package?>?) -> Unit)
    fun addOrUpdatePackage(packageObject: Package, handleResponse: (packageObject: Package?) -> Unit)
    fun getPackagesBasedOnSearch(searchString: String, handleResponse: (packages: List<Package?>?) -> Unit)
    fun deletePackage(packageId: String, handleResponse: (packageObject: Package?) -> Unit)
}