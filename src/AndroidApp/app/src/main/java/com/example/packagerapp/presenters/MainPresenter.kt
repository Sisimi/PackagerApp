package com.example.packagerapp.presenters

import com.example.packagerapp.interactors.RemoteDatabaseInteractor
import com.example.packagerapp.interactors.IRemoteDatabaseInteractor
import com.example.packagerapp.models.Package
import com.example.packagerapp.screens.MainScreen
import dagger.Module
import dagger.Provides
import javax.inject.Inject

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
        //throw NotImplementedError()
        databaseInteractor.getPackages()
    }

    fun searchPackages(packageName: String){
        throw NotImplementedError()
    }
}