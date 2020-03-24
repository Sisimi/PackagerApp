package com.example.packagerapp.di

import com.example.packagerapp.interactors.DatabaseInteractor
import com.example.packagerapp.interactors.IDatabaseInteractor
import com.example.packagerapp.presenters.AddPackagePresenter
import com.example.packagerapp.presenters.MainPresenter
import com.example.packagerapp.presenters.PackageInfoPresenter
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.scope
import org.koin.experimental.builder.singleBy

val interactorModule = module {
    //singleBy<IDatabaseInteractor, DatabaseInteractor>()
    single<IDatabaseInteractor> {
        return@single DatabaseInteractor
    }

}

val presenterModule = module {
    single { MainPresenter }
    single { AddPackagePresenter }
    single { PackageInfoPresenter }
}


val appModules = listOf(interactorModule, presenterModule)