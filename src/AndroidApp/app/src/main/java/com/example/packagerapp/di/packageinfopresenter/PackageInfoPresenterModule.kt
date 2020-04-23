package com.example.packagerapp.di.packageinfopresenter

import com.example.packagerapp.interactors.repositories.ILocalDatabaseRepository
import com.example.packagerapp.interactors.repositories.LocalDatabaseRepository
import com.example.packagerapp.presenters.AddPackagePresenter
import com.example.packagerapp.presenters.PackageInfoPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PackageInfoPresenterModule {

    @Provides
    @Singleton
    fun provideMainPresenterModule(localDatabaseRepository: LocalDatabaseRepository) : PackageInfoPresenter {
        return PackageInfoPresenter(localDatabaseRepository)
    }
}