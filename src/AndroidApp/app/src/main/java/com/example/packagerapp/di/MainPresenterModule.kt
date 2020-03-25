package com.example.packagerapp.di

import com.example.packagerapp.interactors.DatabaseInteractor
import com.example.packagerapp.interactors.IDatabaseInteractor
import com.example.packagerapp.presenters.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainPresenterModule {

    @Provides
    fun provideDatabaseInteractor() : DatabaseInteractor = DatabaseInteractor()

    @Provides
    @Singleton
    fun provideMainPresenterModule(databaseInteractor: DatabaseInteractor) : MainPresenter {
        return MainPresenter(databaseInteractor)
    }
}