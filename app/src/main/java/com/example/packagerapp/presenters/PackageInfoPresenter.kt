package com.example.packagerapp.presenters

import com.example.packagerapp.models.Package
import com.example.packagerapp.screens.PackageInfoScreen

object PackageInfoPresenter: AbstractPresenter<PackageInfoScreen>(){
    var packageObject: Package? = null

    override fun attachScreen(screen: PackageInfoScreen) {
        super.attachScreen(screen)
    }

    override fun detachScreen() {
        super.detachScreen()
    }
}