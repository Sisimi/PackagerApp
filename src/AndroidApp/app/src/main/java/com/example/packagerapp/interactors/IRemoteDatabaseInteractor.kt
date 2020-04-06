package com.example.packagerapp.interactors

import com.example.packagerapp.models.Package
import retrofit2.Callback

interface IRemoteDatabaseInteractor{
    fun getPackages(f: (packages: List<Package?>?) -> Unit)
    fun putPackage(packageObject : Package) : Package?
    fun getPackagesBasedOnSearch(searchString : String) : List<Package?>?
    fun deletePackage()
}