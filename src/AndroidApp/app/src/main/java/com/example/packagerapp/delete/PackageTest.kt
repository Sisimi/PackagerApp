package com.example.packagerapp.delete

import androidx.room.*
import com.example.packagerapp.models.NameValue
import com.google.gson.annotations.SerializedName

@Entity
data class PackageTest(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "packageName") val packageName: String?,
    @ColumnInfo(name = "description") val description: String?,

    @TypeConverters(NameValueConverter::class)
    @SerializedName("nameValueList")
    val nameValueList: List<NameValue>?
)