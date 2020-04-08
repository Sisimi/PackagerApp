package com.example.packagerapp.di


import com.example.packagerapp.interactors.APIs.IRemoteDatabaseAPI
import com.example.packagerapp.interactors.APIs.IRemoteDatabaseInteractor
import com.example.packagerapp.interactors.APIs.RemoteDatabaseInteractor
import com.example.packagerapp.interactors.repositories.ILocalDatabaseRepository
import com.example.packagerapp.interactors.repositories.LocalDatabaseRepository
import com.example.packagerapp.presenters.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainPresenterModule {

    @Provides
    fun provideRemoteDatabaseInteractor(remoteDatabaseAPI : IRemoteDatabaseAPI) : IRemoteDatabaseInteractor {
        return RemoteDatabaseInteractor(
            remoteDatabaseAPI
        )
    }

    @Provides
    fun provideLocalDatabaseRepository() : ILocalDatabaseRepository {
        return LocalDatabaseRepository()
    }

    @Provides
    @Singleton
    fun provideMainPresenterModule(databaseInteractor: RemoteDatabaseInteractor, localDatabaseRepository: LocalDatabaseRepository) : MainPresenter {
        return MainPresenter(databaseInteractor, localDatabaseRepository)
    }
}