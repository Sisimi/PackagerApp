package com.example.packagerapp.data.DAOs

import androidx.room.*
import com.example.packagerapp.models.MyPackage


@Dao
interface MyPackageDAO {
    @Query(value = "Select * FROM MyPackage")
    fun getPackages() : MutableList<MyPackage>

    @Insert
    fun insertNewPackage(packageObject: MyPackage)

    @Query(value = "DELETE FROM MyPackage WHERE id = :packageId")
    fun deletePackage(packageId:String): Int

    @Query(value = "DELETE FROM MyPackage ")
    fun deleteAllPackage(): Int

    @Update
    fun updatePackage(packageObject: MyPackage): Int
}

