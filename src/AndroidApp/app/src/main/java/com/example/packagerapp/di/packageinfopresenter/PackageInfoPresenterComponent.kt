package com.example.packagerapp.di.packageinfopresenter

import com.example.packagerapp.di.addpackagepresenter.AddPackagePresenterModule
import com.example.packagerapp.di.interactors.LocalDatabaseInteractorModule
import com.example.packagerapp.views.packageinfoActivity.PackageInfoActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PackageInfoPresenterModule::class, LocalDatabaseInteractorModule::class])
interface PackageInfoPresenterComponent  {
    fun inject(packageInfoActivity: PackageInfoActivity)
}