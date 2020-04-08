package com.example.packagerapp.mock

import com.example.packagerapp.interactors.APIs.IRemoteDatabaseInteractor
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.models.NameValue

class MockRemoteDatabaseInteractor :
    IRemoteDatabaseInteractor {

    var mockpackageDatabase : MutableList<MyPackage>

    init {
        val testPackage1: MyPackage = MyPackage("-1","Test1", "", null)
        val testPackage2: MyPackage = MyPackage("-2","Test2", "",
            listOf(NameValue("name1","value1"), NameValue("name2","value2")))

        mockpackageDatabase = mutableListOf(testPackage1, testPackage2)

    }

    override fun getPackages(handleResponse: (packages: List<MyPackage?>?) -> Unit) {
        handleResponse(mockpackageDatabase)
    }

    override fun addOrUpdatePackage(
        packageObject: MyPackage,
        handleResponse: (packageObject: MyPackage?) -> Unit
    ) {
        mockpackageDatabase.add(packageObject)
        handleResponse(packageObject)
    }

    override fun getPackagesBasedOnSearch(
        searchString: String,
        handleResponse: (packages: List<MyPackage?>?) -> Unit
    ) {
        var searchResult: MutableList<MyPackage> = mutableListOf()

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
        handleResponse: (packageObject: MyPackage?) -> Unit
    ) {
        var deletedPackage: MyPackage? = null
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