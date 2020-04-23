package com.example.packagerapp.di.interactors

import android.content.Context
import com.example.packagerapp.interactors.repositories.ILocalDatabaseRepository
import com.example.packagerapp.interactors.repositories.LocalDatabaseRepository
import dagger.Module
import dagger.Provides

@Module
class LocalDatabaseInteractorModule constructor(appContext: Context){

    private var appContext = appContext

    @Provides
    fun provideLocalDatabaseRepository() : ILocalDatabaseRepository {
        return LocalDatabaseRepository(appContext)
    }

    @Provides
    fun provideAppContext() :Context{
        return appContext
    }
}