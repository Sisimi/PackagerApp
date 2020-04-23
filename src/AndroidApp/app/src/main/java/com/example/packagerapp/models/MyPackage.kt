package com.example.packagerapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class MyPackage(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "packageName") var packageName: String,
    @ColumnInfo(name = "description") var description: String,

    @TypeConverters(NameValueConverter::class)
    @SerializedName("nameValueList")
    val nameValueList: MutableList<NameValue>
):Parcelable

