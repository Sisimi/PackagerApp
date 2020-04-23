package com.example.packagerapp.di.addpackagepresenter

import android.content.Context
import com.example.packagerapp.interactors.APIs.RemoteDatabaseInteractor
import com.example.packagerapp.interactors.repositories.ILocalDatabaseRepository
import com.example.packagerapp.interactors.repositories.LocalDatabaseRepository
import com.example.packagerapp.presenters.AddPackagePresenter
import com.example.packagerapp.presenters.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AddPackagePresenterModule {

    @Provides
    @Singleton
    fun provideAddPackagePresenterModule(localDatabaseRepository: LocalDatabaseRepository) : AddPackagePresenter {
        return AddPackagePresenter(localDatabaseRepository)
    }

}