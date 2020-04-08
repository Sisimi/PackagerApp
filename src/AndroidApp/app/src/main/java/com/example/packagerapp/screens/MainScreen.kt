package com.example.packagerapp.screens

import com.example.packagerapp.models.MyPackage

interface MainScreen {
    fun openPackageInfoActivity(packageObject: MyPackage)

    fun refreshList(packages: List<MyPackage?>?)
}