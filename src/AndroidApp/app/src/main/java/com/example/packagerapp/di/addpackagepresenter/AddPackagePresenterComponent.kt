package com.example.packagerapp.di.addpackagepresenter

import com.example.packagerapp.di.RemoteDatabaseInteractorModule
import com.example.packagerapp.di.mainpresenter.MainPresenterModule
import com.example.packagerapp.views.addpackage.AddPackageActivity
import com.example.packagerapp.views.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [AddPackagePresenterModule::class])
interface AddPackagePresenterComponent {
    fun inject(addPackageActivity: AddPackageActivity)
}