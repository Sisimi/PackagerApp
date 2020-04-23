package com.example.packagerapp.screens

import com.example.packagerapp.models.MyPackage

interface MainScreen {
    fun handlePackageRemoved(position: Int)
    fun handleScanResult(myPackage: MyPackage?)
}