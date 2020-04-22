package com.example.packagerapp.interactors

import android.os.AsyncTask
import com.example.packagerapp.data.DAOs.MyPackageDAO
import com.example.packagerapp.models.MyPackage

//import com.example.packagerapp.data.DAOs.IPackageDAO

object RepositoryHelper {

    class InsertPackageAsyncTask constructor(packageDAO: MyPackageDAO) : AsyncTask<MyPackage, Unit, Unit>()
    {
        private var dao = packageDAO

        override fun doInBackground(vararg params: MyPackage?) {
            dao.insertNewPackage(params[0]!!)
        }

    }

    class UpdatePackageAsyncTask constructor(packageDAO: MyPackageDAO) : AsyncTask<MyPackage, Unit, Int>()
    {
        private var dao = packageDAO

        override fun doInBackground(vararg params: MyPackage?): Int? {
            return dao.updatePackage(params[0]!!)
        }
    }

    class DeletePackageAsyncTask constructor(packageDAO: MyPackageDAO) : AsyncTask<String, Unit, Int>()
    {
        private var dao = packageDAO

        override fun doInBackground(vararg params: String?): Int? {
            return dao.deletePackage(params[0]!!)
        }
    }

    class GetAllPackagesAsyncTask constructor(packageDAO: MyPackageDAO) : AsyncTask<Unit, Unit, MutableList<MyPackage>?>()
    {
        private var dao = packageDAO

        override fun doInBackground(vararg params: Unit?):  MutableList<MyPackage>? {
            return dao.getPackages()
        }
    }

}