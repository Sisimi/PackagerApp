package com.example.packagerapp.interactors

import com.example.packagerapp.models.Package

interface IRemoteDatabaseInteractor{
    fun getPackages() : List<Package?>?
    fun putPackage()
    fun deletePackage()
}