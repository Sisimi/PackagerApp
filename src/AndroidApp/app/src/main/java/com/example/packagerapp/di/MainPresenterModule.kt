package com.example.packagerapp.di


import com.example.packagerapp.interactors.APIs.IRemoteDatabaseAPI
import com.example.packagerapp.interactors.IRemoteDatabaseInteractor
import com.example.packagerapp.interactors.RemoteDatabaseInteractor
import com.example.packagerapp.presenters.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainPresenterModule {

    @Provides
    fun provideDatabaseInteractor(remoteDatabaseAPI : IRemoteDatabaseAPI) : IRemoteDatabaseInteractor {
        return RemoteDatabaseInteractor(remoteDatabaseAPI)
    }

    @Provides
    @Singleton
    fun provideMainPresenterModule(databaseInteractor: RemoteDatabaseInteractor) : MainPresenter {
        return MainPresenter(databaseInteractor)
    }
}