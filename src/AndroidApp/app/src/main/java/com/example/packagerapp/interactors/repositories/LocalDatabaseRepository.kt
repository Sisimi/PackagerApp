package com.example.packagerapp.interactors.repositories
//import com.example.packagerapp.data.DAOs.IPackageDAO
//import com.example.packagerapp.data.PackagesDatabase
import com.example.packagerapp.data.DAOs.MyPackageDAO
import com.example.packagerapp.data.MyPackagesDatabase
import com.example.packagerapp.interactors.RepositoryHelper
import com.example.packagerapp.misc.appContext
import com.example.packagerapp.models.MyPackage
import javax.inject.Inject

class LocalDatabaseRepository @Inject constructor() :
    ILocalDatabaseRepository {
    private var myPackageDAO: MyPackageDAO


    init {
        val database: MyPackagesDatabase = MyPackagesDatabase.getInstance(appContext!!)

        myPackageDAO = database.packageDAO()
    }


    override fun insert(packageObject: MyPackage): MyPackage? {
        var insertPackageTask =
            RepositoryHelper.InsertPackageAsyncTask(
                myPackageDAO
            )
        insertPackageTask.execute(packageObject)

        return packageObject
    }

    override fun delete(packageObject: MyPackage): MyPackage? {
        var deletePackageTask =
            RepositoryHelper.DeletePackageAsyncTask(
                myPackageDAO
            )

        deletePackageTask.execute(packageObject)

        if(deletePackageTask.get() > 0)
        {
            return packageObject
        }

        return null
    }

    override fun getAll(): List<MyPackage?>? {
        var getAllPackagesAsyncTask = RepositoryHelper.GetAllPackagesAsyncTask(
            myPackageDAO
        )
        getAllPackagesAsyncTask.execute()

        return getAllPackagesAsyncTask.get()
    }

    override fun update(packageObject: MyPackage): MyPackage?{
        var updatePackageTask =
            RepositoryHelper.UpdatePackageAsyncTask(
                myPackageDAO
            )
        updatePackageTask.execute(packageObject)

        if(updatePackageTask.get() > 0)
        {
            return packageObject
        }

        return null
    }


}