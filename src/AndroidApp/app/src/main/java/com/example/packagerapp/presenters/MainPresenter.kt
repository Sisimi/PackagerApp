package com.example.packagerapp.presenters

import com.example.packagerapp.interactors.APIs.IRemoteDatabaseInteractor
import com.example.packagerapp.interactors.repositories.ILocalDatabaseRepository
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.screens.MainScreen
import java.util.function.Predicate
import javax.inject.Inject

class MainPresenter
    @Inject constructor(
        private var remoteDatabaseInteractor: IRemoteDatabaseInteractor,
        private var localDatabaseRepository: ILocalDatabaseRepository
    )
    : AbstractPresenter<MainScreen>() {

    fun startQRScan() {
        //TODO: implement the scan logic in the future
        //temporary solution
        var temp = localDatabaseRepository.getAll()

        if(temp != null && temp.count() > 0)
        {
            screen!!.handleScanResult(temp[0])
        }
        else
        {
            screen!!.handleScanResult(null)
        }
    }

    fun getLocalData() : MutableList<MyPackage> {
        return localDatabaseRepository.getAll() ?: mutableListOf()
    }

    fun deletePackage(id: String)
    {
        localDatabaseRepository.delete(id)

        remoteDatabaseInteractor.deletePackage(id) { returnedData ->
            //currently nothing
        }

    }

    fun refreshDB() {
        var localPackages = localDatabaseRepository.getAll() ?: mutableListOf()

        remoteDatabaseInteractor.addManyPackages(localPackages)
    }



}