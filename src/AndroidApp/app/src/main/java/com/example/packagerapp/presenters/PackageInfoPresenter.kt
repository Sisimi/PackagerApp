package com.example.packagerapp.presenters

import com.example.packagerapp.interactors.APIs.IRemoteDatabaseInteractor
import com.example.packagerapp.interactors.repositories.ILocalDatabaseRepository
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.screens.PackageInfoScreen
import javax.inject.Inject

class PackageInfoPresenter   @Inject constructor(
    private var localDatabaseRepository: ILocalDatabaseRepository
): AbstractPresenter<PackageInfoScreen>(){
    lateinit var myPackage: MyPackage

    //TODO:Theres code duplication with MainPresenter but i have no idea at the moment how to resolve this issue
    fun scanQRCode()
    {
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

}