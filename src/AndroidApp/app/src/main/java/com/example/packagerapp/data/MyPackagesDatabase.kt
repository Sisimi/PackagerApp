package com.example.packagerapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.packagerapp.data.DAOs.MyPackageDAO
import com.example.packagerapp.models.NameValueConverter
import com.example.packagerapp.models.MyPackage


@Database(entities = [MyPackage::class], version = 2)
@TypeConverters(NameValueConverter::class)
abstract class MyPackagesDatabase: RoomDatabase() {

    abstract fun packageDAO() : MyPackageDAO

    companion object
    {
        private var instance: MyPackagesDatabase? = null

        fun getInstance(context: Context): MyPackagesDatabase {
            if (instance == null)
            {
                instance = Room.databaseBuilder(context.applicationContext,
                    MyPackagesDatabase::class.java, "packages.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!
        }
    }
}