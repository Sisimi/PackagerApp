package com.example.packagerapp.screens

import com.example.packagerapp.models.MyPackage

interface PackageInfoScreen {
    fun refreshInfoList(packageObject: MyPackage)
}