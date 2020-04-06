package com.example.packagerapp.di

import com.example.packagerapp.presenters.MainPresenter
import com.example.packagerapp.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [MainPresenterModule::class, RemoteDatabaseInteractorModule::class])
interface MainPresenterComponent {
    //fun getMainPresenter(): MainPresenter
    fun inject(mainActivity: MainActivity)
}