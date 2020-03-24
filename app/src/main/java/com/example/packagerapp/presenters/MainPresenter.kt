package com.example.packagerapp.presenters

import com.example.packagerapp.interactors.IDatabaseInteractor
import com.example.packagerapp.models.Package
import com.example.packagerapp.screens.MainScreen

import org.koin.android.ext.android.get
import org.koin.android.ext.android.startKoin
import org.koin.standalone.KoinComponent
import org.koin.standalone.get

object MainPresenter : AbstractPresenter<MainScreen>(), KoinComponent{

    val databaseInteractor: IDatabaseInteractor = get()

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
        throw NotImplementedError()
    }

    fun searchPackages(packageName: String){
        throw NotImplementedError()
    }
}