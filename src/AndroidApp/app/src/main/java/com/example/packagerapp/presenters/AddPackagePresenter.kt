package com.example.packagerapp.presenters

import com.example.packagerapp.models.NameValue
import com.example.packagerapp.screens.AddPackageScreen
import com.example.packagerapp.models.Package

object AddPackagePresenter : AbstractPresenter<AddPackageScreen>(){
    val newPackage = Package(null, null, null, null)

    override fun attachScreen(screen: AddPackageScreen) {
        super.attachScreen(screen)
    }

    override fun detachScreen() {
        super.detachScreen()
    }

    fun setPackageName(packageName: String){
        throw NotImplementedError()
    }

    fun setPackageDescription(desc: String){
        throw NotImplementedError()
    }

    fun addNameValue(value: NameValue){
        throw NotImplementedError()
    }

    fun getNewPackage(){
        throw NotImplementedError()
    }

}