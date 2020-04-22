package com.example.packagerapp.presenters

import android.view.View
import com.example.packagerapp.interactors.APIs.IRemoteDatabaseInteractor
import com.example.packagerapp.interactors.repositories.ILocalDatabaseRepository
import com.example.packagerapp.models.NameValue
import com.example.packagerapp.screens.AddPackageScreen
import com.example.packagerapp.models.MyPackage
import kotlinx.android.synthetic.main.activity_add_package.*
import java.util.*
import javax.inject.Inject

class AddPackagePresenter  @Inject constructor(
    private var localDatabaseRepository: ILocalDatabaseRepository
) : AbstractPresenter<AddPackageScreen>(){
    //TODO:maybe the model is not good because it demands an id, which must be handled on server side later
    var myPackage = GenerateNewPackage()


    fun addPackageToDB()
    {
        localDatabaseRepository.insert(myPackage)
    }

    fun setMyPackageDescription(desc: String){
        myPackage.description = normalizeInput(desc)
    }


    fun addNewItemToNameValueList(nameValue: NameValue) : Unit
    {
        myPackage.nameValueList.add(nameValue)
        validatePackage()
    }

    fun removeItemFromNameValueList(position: Int) : Unit
    {
        myPackage.nameValueList.removeAt(position)
        validatePackage()
    }

    fun setMyPackageName(packageName: String) : Unit
    {
        myPackage.packageName = packageName
        validatePackage()
    }


    //TODO: extend validation for prohibited characters
    private fun validatePackage()
    {
        if(myPackage.packageName == "" || myPackage.packageName == null
            || myPackage.description == null || myPackage.nameValueList.count() <= 0 )
        {
            screen!!.handlePackageValidation(false);
        }
        else
        {
            screen!!.handlePackageValidation(true);
        }
    }

    //TODO: implement trim and other methods for input handling
    private fun normalizeInput(input: String) : String
    {
        return input
    }

    private fun GenerateNewPackage() : MyPackage
    {
        return MyPackage(UUID.randomUUID().toString(), "","", mutableListOf())
    }

}