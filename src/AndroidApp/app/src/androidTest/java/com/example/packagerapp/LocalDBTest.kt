package com.example.packagerapp

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.packagerapp.data.MyPackagesDatabase
import com.example.packagerapp.interactors.repositories.ILocalDatabaseRepository
import com.example.packagerapp.interactors.repositories.LocalDatabaseRepository
import com.example.packagerapp.models.MyPackage
import com.example.packagerapp.models.NameValue
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class LocalDBTest {

    private lateinit var localDBRepository: ILocalDatabaseRepository

    @Before
    fun createDB()
    {
        val context =  getApplicationContext<Context>()
        //db = Room.inMemoryDatabaseBuilder(context, MyPackagesDatabase::class.java).build()
        //userDao = db.getUserDao()
        localDBRepository = LocalDatabaseRepository(context)
    }

    @After
    fun truncDB()
    {
        localDBRepository.deleteAll()
    }


    @Test
    fun insertTest() {
        var testPackage = MyPackage(UUID.randomUUID().toString(),"InsertTestPackage","", mutableListOf())

        localDBRepository.insert(testPackage)

        var packageList = localDBRepository.getAll()

        assertNotNull(packageList)
        assertEquals(1, packageList!!.count())
        assertEquals(testPackage.id, packageList[0].id)
    }

    @Test
    fun getAllTest() {
        var testPackage1 = MyPackage(UUID.randomUUID().toString(),"InsertTestPackage1","", mutableListOf())
        var testPackage2 = MyPackage(UUID.randomUUID().toString(),"InsertTestPackage2","", mutableListOf())

        localDBRepository.insert(testPackage1)
        localDBRepository.insert(testPackage2)

        var packageList = localDBRepository.getAll()

        assertNotNull(packageList)
        assertEquals(2,packageList!!.count())
        assertEquals(testPackage1.id,packageList[0].id)
        assertEquals(testPackage2.id, packageList[1].id)
    }

    @Test
    fun deleteAllTest()
    {
        var testPackage1 = MyPackage(UUID.randomUUID().toString(),"InsertTestPackage1","", mutableListOf())
        var testPackage2 = MyPackage(UUID.randomUUID().toString(),"InsertTestPackage2","", mutableListOf())
        var testPackage3 = MyPackage(UUID.randomUUID().toString(),"InsertTestPackage3","", mutableListOf())
        var testPackage4 = MyPackage(UUID.randomUUID().toString(),"InsertTestPackage4","", mutableListOf())

        localDBRepository.insert(testPackage1)
        localDBRepository.insert(testPackage2)
        localDBRepository.insert(testPackage3)
        localDBRepository.insert(testPackage4)

        localDBRepository.deleteAll()

        var packageList = localDBRepository.getAll()

        assertNotNull(packageList)
        assertEquals(0,packageList!!.count())
    }

    @Test
    fun deleteOneTest()
    {
        var deletedItemId = UUID.randomUUID().toString()

        var testPackage1 = MyPackage(UUID.randomUUID().toString(),"InsertTestPackage1","", mutableListOf())
        var testPackage2 = MyPackage(UUID.randomUUID().toString(),"InsertTestPackage2","", mutableListOf())
        var testPackage3 = MyPackage(deletedItemId,"InsertTestPackage3","", mutableListOf())
        var testPackage4 = MyPackage(UUID.randomUUID().toString(),"InsertTestPackage4","", mutableListOf())

        localDBRepository.insert(testPackage1)
        localDBRepository.insert(testPackage2)
        localDBRepository.insert(testPackage3)
        localDBRepository.insert(testPackage4)

        localDBRepository.delete(deletedItemId)

        var packageList = localDBRepository.getAll()

        assertNotNull(packageList)
        assertEquals(3,packageList!!.count())

        for(myPackage in packageList)
        {
            assertNotEquals(deletedItemId, myPackage.id)
        }
    }

    @Test
    fun updateTest()
    {
        var originalName = "TestPacakge"
        var updatedPackageName = "UpdateTestPackage"

        var testPackage = MyPackage(UUID.randomUUID().toString(),originalName,"", mutableListOf())

        localDBRepository.insert(testPackage)

        var packageList = localDBRepository.getAll()
        assertNotNull(packageList)
        assertEquals(1, packageList!!.count())

        var myPackage = packageList!![0]
        myPackage.packageName = updatedPackageName
        localDBRepository.update(myPackage)

        packageList = localDBRepository.getAll()
        assertNotNull(packageList)
        assertEquals(1, packageList!!.count())

        var updatedPackage = packageList!![0]
        assertEquals(updatedPackageName, updatedPackage.packageName)
    }

    @Test
    fun nameValueSaveTest()
    {
        var nameValueTestName1 = "TestName11"
        var nameValueTestValue1 = "TestValue11"
        var nameValueTestName2 = "TestName22"
        var nameValueTestValue2 = "TestValue22"
        var nameValueTestName3 = "TestName33"
        var nameValueTestValue3 = "TestValue33"


        var nameValueTest1 : MutableList<NameValue> = mutableListOf()
        var nameValueTest2 : MutableList<NameValue> = mutableListOf(NameValue(nameValueTestName1, nameValueTestValue1))
        var nameValueTest3 : MutableList<NameValue> = mutableListOf(NameValue("TestName21", "TestValue21"), NameValue(nameValueTestName2, nameValueTestValue2))
        var nameValueTest4 : MutableList<NameValue> = mutableListOf(NameValue("TestName31", "TestValue31"), NameValue("TestName32", "TestValue32"), NameValue(nameValueTestName3, nameValueTestValue3))

        var testPackage1 = MyPackage(UUID.randomUUID().toString(),"NameValueTestPackage1","", nameValueTest1)
        var testPackage2 = MyPackage(UUID.randomUUID().toString(),"NameValueTestPackage2","", nameValueTest2)
        var testPackage3 = MyPackage(UUID.randomUUID().toString(),"NameValueTestPackage3","", nameValueTest3)
        var testPackage4 = MyPackage(UUID.randomUUID().toString(),"NameValueTestPackage4","", nameValueTest4)


        localDBRepository.insert(testPackage1)
        localDBRepository.insert(testPackage2)
        localDBRepository.insert(testPackage3)
        localDBRepository.insert(testPackage4)

        var packageList = localDBRepository.getAll()

        assertNotNull(packageList)
        assertEquals(4,packageList!!.count())

        assertEquals(0,packageList!![0].nameValueList.count())
        assertEquals(1,packageList!![1].nameValueList.count())
        assertEquals(2,packageList!![2].nameValueList.count())
        assertEquals(3,packageList!![3].nameValueList.count())

        assertEquals(nameValueTestName1, packageList!![1].nameValueList[0].name)
        assertEquals(nameValueTestValue1, packageList!![1].nameValueList[0].value)

        assertEquals(nameValueTestName2, packageList!![2].nameValueList[1].name)
        assertEquals(nameValueTestValue2, packageList!![2].nameValueList[1].value)

        assertEquals(nameValueTestName3, packageList!![3].nameValueList[2].name)
        assertEquals(nameValueTestValue3, packageList!![3].nameValueList[2].value)
    }

}