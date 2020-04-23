package com.example.packagerapp.interactors.APIs

import retrofit2.Call
import retrofit2.http.*
import com.example.packagerapp.models.*;


interface IRemoteDatabaseAPI {

    @GET("packages")
    fun packagerGetAllPackages(): Call<List<MyPackage>?>?

    @Headers("Content-Type:application/json")
    @PUT("package/add")
    fun packagerPutOrUpdatePackage(
        @Body packageObject: MyPackage?
    ): Call<MyPackage?>?

    @Headers("Content-Type:application/json")
    @PUT("package/addMany")
    fun packagerPutManyPackage(
        @Body packageObject: List<MyPackage>
    ): Call<Unit?>?

    @GET("package/{searchString}")
    fun packagerGetSpecificPackages(
        @Path("searchString") searchString: String?
    ): Call<List<MyPackage>?>?

    @DELETE("package/delete/{Id}")
    fun packagerDeletePackage(
        @Path("Id") Id: String?
    ): Call<MyPackage>?
}