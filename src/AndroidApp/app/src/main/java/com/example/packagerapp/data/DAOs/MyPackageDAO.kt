package com.example.packagerapp.data.DAOs

import androidx.room.*
import com.example.packagerapp.models.MyPackage


@Dao
interface MyPackageDAO {
    @Query(value = "Select * FROM MyPackage")
    fun getPackages() : List<MyPackage>

    @Insert
    fun insertNewPackage(packageObject: MyPackage)

    //@Query(value = "DELETE FROM MyPackage WHERE id = :packageId")
    @Delete
    fun deletePackage(packageObject: MyPackage): Int

    @Update
    fun updatePackage(packageObject: MyPackage): Int
}

