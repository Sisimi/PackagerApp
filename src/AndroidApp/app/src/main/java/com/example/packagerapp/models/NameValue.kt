package com.example.packagerapp.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NameValue(
    val name: String,
    val value: String
) : Parcelable