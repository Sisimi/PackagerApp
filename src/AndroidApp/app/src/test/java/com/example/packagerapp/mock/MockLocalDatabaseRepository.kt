package com.example.packagerapp.mock

import com.example.packagerapp.interactors.repositories.ILocalDatabaseRepository
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.models.NameValue

class MockLocalDatabaseRepository : ILocalDatabaseRepository {
    var mockpackageDatabase : MutableList<MyPackage>

    init {
        val testPackage1: MyPackage = MyPackage("-1","Test1", "", mutableListOf())
        val testPackage2: MyPackage = MyPackage("-2","Test2", "",
            mutableListOf(NameValue("name1","value1"), NameValue("name2","value2"))
        )

        mockpackageDatabase = mutableListOf(testPackage1, testPackage2)

    }

    override fun insert(packageObject: MyPackage): MyPackage {
        mockpackageDatabase.add(packageObject)
        return packageObject
    }

    override fun delete(packageObject: MyPackage): MyPackage {
        mockpackageDatabase.remove(packageObject)
        return packageObject
    }

    override fun getAll(): List<MyPackage>? {
        return mockpackageDatabase
    }

    override fun update(packageObject: MyPackage): MyPackage? {
        mockpackageDatabase.add(packageObject)
        return packageObject
    }

}