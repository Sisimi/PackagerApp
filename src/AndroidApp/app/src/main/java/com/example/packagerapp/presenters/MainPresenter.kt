package com.example.packagerapp.presenters

import com.example.packagerapp.interactors.APIs.IRemoteDatabaseInteractor
import com.example.packagerapp.interactors.repositories.ILocalDatabaseRepository
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.screens.MainScreen
import javax.inject.Inject

class MainPresenter
    @Inject constructor(
        private var remoteDatabaseInteractor: IRemoteDatabaseInteractor,
        private var localDatabaseRepository: ILocalDatabaseRepository
    )
    : AbstractPresenter<MainScreen>() {

    private var packagesCache: List<MyPackage>? = localDatabaseRepository.getAll()

    fun startQRScan() {
        throw NotImplementedError()
    }

    fun getPackages() {
        remoteDatabaseInteractor.getPackages ( fun (packages:List<MyPackage?>?){
            screen?.refreshList(packages)
        })

        var result = localDatabaseRepository.getAll()
    }

    fun addPackage() {

    }

    fun modifyPackage() {

    }

    fun searchPackages(packageName: String){
        throw NotImplementedError()
    }
}