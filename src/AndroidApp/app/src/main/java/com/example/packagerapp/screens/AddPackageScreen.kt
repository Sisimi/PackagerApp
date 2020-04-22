package com.example.packagerapp.screens

import com.example.packagerapp.models.NameValue

interface AddPackageScreen{
    fun handlePackageValidation(isValid: Boolean)
    fun notifyOnItemRemoved(position: Int)
}