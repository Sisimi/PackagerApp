package com.example.packagerapp.di

import com.example.packagerapp.interactors.DatabaseInteractor
import com.example.packagerapp.interactors.IDatabaseInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DatabaseInteractorModule{

    @Binds
    abstract fun provideDatabaseInteractor(databaseInteractor: DatabaseInteractor) : IDatabaseInteractor
}