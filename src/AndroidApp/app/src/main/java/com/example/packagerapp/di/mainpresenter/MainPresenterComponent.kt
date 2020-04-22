package com.example.packagerapp.di.mainpresenter

import com.example.packagerapp.di.RemoteDatabaseInteractorModule
import com.example.packagerapp.views.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [MainPresenterModule::class, RemoteDatabaseInteractorModule::class])
interface MainPresenterComponent {
    fun inject(mainActivity: MainActivity)
}