package com.example.packagerapp.interactors.APIs

import retrofit2.Call
import retrofit2.http.*
import com.example.packagerapp.models.*;


interface IRemoteDatabaseAPI {

    @GET("packages")
    fun packagerGetAllPackages(): Call<List<Package?>?>?

    @Headers("Content-Type:application/json")
    @PUT("package/add")
    fun packagerPutOrUpdatePackage(
        @Body packageObject: Package?
    ): Call<Package?>?

    @GET("package/{searchString}")
    fun packagerGetSpecificPackages(
        @Path("searchString") searchString: String?
    ): Call<List<Package?>?>?

    @DELETE("package/delete/{Id}")
    fun packagerDeletePackage(
        @Path("Id") Id: String?
    ): Call<Package?>?
}