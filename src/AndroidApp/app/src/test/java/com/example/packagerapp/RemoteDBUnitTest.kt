package com.example.packagerapp

import com.example.packagerapp.interactors.APIs.IRemoteDatabaseAPI
import com.example.packagerapp.interactors.APIs.IRemoteDatabaseInteractor
import com.example.packagerapp.interactors.APIs.RemoteDatabaseInteractor
import com.example.packagerapp.models.MyPackage
import com.google.gson.Gson
import okhttp3.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//This implementation cannot be tested
class RemoteDBUnitTest {

    private lateinit var remoteDatabaseInteractor : IRemoteDatabaseInteractor

    private var mockWebServer = MockWebServer()


    @Before
    fun apiInit()
    {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val remoteDatabaseAPIInstance: IRemoteDatabaseAPI by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(IRemoteDatabaseAPI::class.java)
        }

        remoteDatabaseInteractor = RemoteDatabaseInteractor(remoteDatabaseAPIInstance)
    }

    @Test
    fun addorUpdateOne()
    {
        var myPackageTest1 = MyPackage("-1","Test1","", mutableListOf())

        var json = Gson().toJson(myPackageTest1)

        mockWebServer.enqueue(MockResponse().setBody(json))
        mockWebServer.start()

        remoteDatabaseInteractor.addOrUpdatePackage(myPackageTest1, handleResponse = { insertedPacakge->
            assertNotNull(insertedPacakge)
            assertEquals(myPackageTest1.id, insertedPacakge!!.id)
        })
    }

    @Test
    fun addMany()
    {
        var myPackageTest1 = MyPackage("-1","Test1","", mutableListOf())
        var myPackageTest2 = MyPackage("-2","Test2","", mutableListOf())

        var packages = mutableListOf(myPackageTest1, myPackageTest2)

        mockWebServer.enqueue(MockResponse())
        mockWebServer.start()

        remoteDatabaseInteractor.addManyPackages(packages)
    }

    @Test
    fun getPackages()
    {
        var myPackageTest1 = MyPackage("-1","Test1","", mutableListOf())
        var myPackageTest2= MyPackage("-1","Test1","", mutableListOf())
        var json = Gson().toJson(mutableListOf(myPackageTest1,myPackageTest2))

        mockWebServer.enqueue(MockResponse().setBody(json))
        mockWebServer.start()

        remoteDatabaseInteractor.getPackages(handleResponse = {packages->
            assertNotNull(packages)
            assertEquals(packages!![0].id, myPackageTest1.id)
            assertEquals(packages!![1].id, myPackageTest2.id)
        })

    }

    @Test
    fun deleteOne()
    {
        var myPackageTest = MyPackage("-1","Test1","", mutableListOf())
        var json = Gson().toJson(myPackageTest)

        mockWebServer.enqueue(MockResponse().setBody(json))
        mockWebServer.start()

        remoteDatabaseInteractor.deletePackage(myPackageTest.id, handleResponse = {returnedPackage ->
            assertNotNull(returnedPackage)
            assertEquals(myPackageTest.id, returnedPackage!!.id)
        })
    }
}