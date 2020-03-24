package com.example.packagerapp.presenters

abstract class AbstractPresenter<S> {
    protected var screen: S? = null

    open fun attachScreen(screen: S){
        this.screen = screen
    }

    open fun detachScreen(){
        this.screen = null
    }
}