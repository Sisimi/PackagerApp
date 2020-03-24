package com.example.packagerapp.presenters

import com.example.packagerapp.interactors.IDatabaseInteractor
import com.example.packagerapp.models.Package
import com.example.packagerapp.screens.MainScreen
import com.example.packagerapp.interactors.DatabaseInteractor
import javax.inject.Inject

object MainPresenter : AbstractPresenter<MainScreen>(){
    @Inject
    lateinit var databaseInteractor: DatabaseInteractor

    private val packagesCache: List<Package>? = null

    init {

    }

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