package com.example.packagerapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.packagerapp.delete.NameValueConverter
import com.google.gson.annotations.SerializedName

@Entity
data class MyPackage(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "packageName") var packageName: String,
    @ColumnInfo(name = "description") var description: String,

    @TypeConverters(NameValueConverter::class)
    @SerializedName("nameValueList")
    val nameValueList: MutableList<NameValue>
)

