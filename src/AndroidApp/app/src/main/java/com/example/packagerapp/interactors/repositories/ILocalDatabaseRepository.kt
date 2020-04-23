package com.example.packagerapp.interactors.repositories

import com.example.packagerapp.models.MyPackage

interface ILocalDatabaseRepository {
    fun insert(packageObject: MyPackage): MyPackage?
    fun delete(id:String)
    fun deleteAll()
    fun getAll(): MutableList<MyPackage>?
    fun update(packageObject: MyPackage): MyPackage?
}