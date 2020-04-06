package io.swagger.client.api;

import io.swagger.client.CollectionFormats.*;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.MultipartBody;

import io.swagger.client.model.ModelPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PackagerApi {
  /**
   * 
   * 
   * @param _package  (required)
   * @return Call&lt;ModelPackage&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/package/add")
  Call<ModelPackage> packagerAddPackage(
    @retrofit2.http.Body ModelPackage _package
  );

  /**
   * 
   * 
   * @return Call&lt;List&lt;ModelPackage&gt;&gt;
   */
  @GET("api/packages")
  Call<List<ModelPackage>> packagerGetAllPackages();
    

  /**
   * 
   * 
   * @param searchString  (required)
   * @return Call&lt;List&lt;ModelPackage&gt;&gt;
   */
  @GET("api/package/{searchString}")
  Call<List<ModelPackage>> packagerGetSpecificPackages(
    @retrofit2.http.Path("searchString") String searchString
  );

  /**
   * 
   * 
   * @return Call&lt;String&gt;
   */
  @GET("api")
  Call<String> packagerHealthCheck();
    

}
