package com.example.packagerapp.interactors.repositories

import com.example.packagerapp.models.MyPackage

interface ILocalDatabaseRepository {
    fun insert(packageObject: MyPackage): MyPackage?
    fun delete(packageObject: MyPackage): MyPackage?
    fun getAll(): List<MyPackage>?
    fun update(packageObject: MyPackage): MyPackage?
}