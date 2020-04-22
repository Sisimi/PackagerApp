package com.example.packagerapp.di.packageinfopresenter

import com.example.packagerapp.di.addpackagepresenter.AddPackagePresenterModule
import com.example.packagerapp.views.packageinfoActivity.PackageInfoActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AddPackagePresenterModule::class])
interface PackageInfoPresenterComponent  {
    fun inject(packageInfoActivity: PackageInfoActivity)
}