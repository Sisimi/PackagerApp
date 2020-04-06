package com.example.packagerapp.interactors.APIs

import retrofit2.Call
import retrofit2.http.*
import com.example.packagerapp.models.*;


interface IRemoteDatabaseAPI {
    @Headers("Content-Type:application/json")
    @PUT("package/add")
    fun packagerAddPackage(
        @Body packageObject: Package?
    ): Call<Package?>?


    @GET("packages")
    fun packagerGetAllPackages(): Call<List<Package?>?>?


    @GET("package/{searchString}")
    fun packagerGetSpecificPackages(
        @Path("searchString") searchString: String?
    ): Call<List<Package?>?>?
}