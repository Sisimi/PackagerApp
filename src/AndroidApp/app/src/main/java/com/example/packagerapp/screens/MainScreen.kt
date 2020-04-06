package com.example.packagerapp.screens

import com.example.packagerapp.models.Package

interface MainScreen {
    fun openPackageInfoActivity(packageObject: Package)

    fun refreshList(packages: List<Package?>?)
}