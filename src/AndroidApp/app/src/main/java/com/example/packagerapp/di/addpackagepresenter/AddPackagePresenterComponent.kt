package com.example.packagerapp.di.addpackagepresenter

import com.example.packagerapp.di.interactors.LocalDatabaseInteractorModule
import com.example.packagerapp.views.addpackage.AddPackageActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [AddPackagePresenterModule::class, LocalDatabaseInteractorModule::class])
interface AddPackagePresenterComponent {
    fun inject(addPackageActivity: AddPackageActivity)
}