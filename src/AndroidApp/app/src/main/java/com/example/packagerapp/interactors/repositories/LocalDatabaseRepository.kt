package com.example.packagerapp.interactors.repositories
//import com.example.packagerapp.data.DAOs.IPackageDAO
//import com.example.packagerapp.data.PackagesDatabase
import android.content.Context
import com.example.packagerapp.data.DAOs.MyPackageDAO
import com.example.packagerapp.data.MyPackagesDatabase
import com.example.packagerapp.models.MyPackage
import javax.inject.Inject



class LocalDatabaseRepository @Inject constructor(appContext : Context) :
    ILocalDatabaseRepository {
    private var myPackageDAO: MyPackageDAO



    init {
        val database: MyPackagesDatabase = MyPackagesDatabase.getInstance(appContext)

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

    override fun delete(id:String) {
        var deletePackageTask =
            RepositoryHelper.DeletePackageAsyncTask(myPackageDAO)

        deletePackageTask.execute(id)

        return
    }

    override fun deleteAll() {
        var deleteAllPackageTask =
            RepositoryHelper.DeleteAllPackageAsyncTask(myPackageDAO)

        deleteAllPackageTask.execute()
    }

    override fun getAll(): MutableList<MyPackage>? {
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