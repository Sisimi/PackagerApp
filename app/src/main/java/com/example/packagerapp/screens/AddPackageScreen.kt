package com.example.packagerapp.screens

import com.example.packagerapp.models.NameValue

interface AddPackageScreen{
    fun setPackageNameText(name: String)
    fun setPackageDescription(desc: String)
    fun refreshList(addedValues: List<NameValue>)
}