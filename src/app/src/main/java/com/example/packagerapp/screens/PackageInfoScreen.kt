package com.example.packagerapp.screens

import com.example.packagerapp.models.Package

interface PackageInfoScreen {
    fun refreshInfoList(packageObject: Package)
}