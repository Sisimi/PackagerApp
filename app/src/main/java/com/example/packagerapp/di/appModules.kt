package com.example.packagerapp.di

import com.example.packagerapp.interactors.DatabaseInteractor
import com.example.packagerapp.interactors.IDatabaseInteractor
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.scope
import org.koin.experimental.builder.singleBy

val databaseInteractorModule = module {
    singleBy<IDatabaseInteractor, DatabaseInteractor>()
}


val appModules = listOf(databaseInteractorModule)