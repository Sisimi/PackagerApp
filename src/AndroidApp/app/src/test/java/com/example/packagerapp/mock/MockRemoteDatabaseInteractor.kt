package com.example.packagerapp.mock

import com.example.packagerapp.interactors.IRemoteDatabaseInteractor
import com.example.packagerapp.models.NameValue
import com.example.packagerapp.models.Package

class MockRemoteDatabaseInteractor :IRemoteDatabaseInteractor{

    var mockpackageDatabase : MutableList<Package>

    init {
        val testPackage1: Package = Package("-1","Test1", "", null)
        val testPackage2: Package = Package("-2","Test2", "",
            listOf(NameValue("name1","value1"), NameValue("name2","value2")))

        mockpackageDatabase = mutableListOf(testPackage1, testPackage2)

    }

    override fun getPackages(handleResponse: (packages: List<Package?>?) -> Unit) {
        handleResponse(mockpackageDatabase)
    }

    override fun addOrUpdatePackage(
        packageObject: Package,
        handleResponse: (packageObject: Package?) -> Unit
    ) {
        mockpackageDatabase.add(packageObject)
        handleResponse(packageObject)
    }

    override fun getPackagesBasedOnSearch(
        searchString: String,
        handleResponse: (packages: List<Package?>?) -> Unit
    ) {
        var searchResult: MutableList<Package> = mutableListOf()

        for(packageObject in mockpackageDatabase)
        {
            if(packageObject.packageName!!.startsWith(searchString,true))
            {
                searchResult.add(packageObject)
            }
        }
    }

    override fun deletePackage(
        packageId: String,
        handleResponse: (packageObject: Package?) -> Unit
    ) {
        var deletedPackage: Package? = null
        for (packageObject in mockpackageDatabase)
        {
            if(packageObject.id == packageId)
            {
                deletedPackage = packageObject
                mockpackageDatabase.remove(packageObject)
            }
        }

        handleResponse(deletedPackage)
    }

}