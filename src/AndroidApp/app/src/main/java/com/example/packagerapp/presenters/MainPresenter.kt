package com.example.packagerapp.presenters

import android.util.Log
import com.example.packagerapp.interactors.RemoteDatabaseInteractor
import com.example.packagerapp.interactors.IRemoteDatabaseInteractor
import com.example.packagerapp.models.Package
import com.example.packagerapp.screens.MainScreen
import dagger.Module
import dagger.Provides
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response

class MainPresenter
    @Inject constructor(private var databaseInteractor: IRemoteDatabaseInteractor)
    : AbstractPresenter<MainScreen>() {

    private val packagesCache: List<Package>? = null

    override fun attachScreen(screen: MainScreen) {
        super.attachScreen(screen)
    }

    override fun detachScreen() {
        super.detachScreen()
    }

    fun startQRScan() {
        throw NotImplementedError()
    }

    fun getPackages() {
        databaseInteractor.getPackages ( fun (packages:List<Package?>?){
            screen?.refreshList(packages)
        })
    }

    fun addPackage() {

    }

    fun modifyPackage() {

    }

    fun searchPackages(packageName: String){
        throw NotImplementedError()
    }
}