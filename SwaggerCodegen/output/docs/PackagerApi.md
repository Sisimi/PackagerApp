# PackagerApi

All URIs are relative to *https://localhost:5001*

Method | HTTP request | Description
------------- | ------------- | -------------
[**packagerAddPackage**](PackagerApi.md#packagerAddPackage) | **PUT** api/package/add | 
[**packagerGetAllPackages**](PackagerApi.md#packagerGetAllPackages) | **GET** api/packages | 
[**packagerGetSpecificPackages**](PackagerApi.md#packagerGetSpecificPackages) | **GET** api/package/{searchString} | 
[**packagerHealthCheck**](PackagerApi.md#packagerHealthCheck) | **GET** api | 


<a name="packagerAddPackage"></a>
# **packagerAddPackage**
> ModelPackage packagerAddPackage(_package)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PackagerApi;


PackagerApi apiInstance = new PackagerApi();
ModelPackage _package = new ModelPackage(); // ModelPackage | 
try {
    ModelPackage result = apiInstance.packagerAddPackage(_package);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PackagerApi#packagerAddPackage");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **_package** | [**ModelPackage**](ModelPackage.md)|  |

### Return type

[**ModelPackage**](ModelPackage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="packagerGetAllPackages"></a>
# **packagerGetAllPackages**
> List&lt;ModelPackage&gt; packagerGetAllPackages()



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PackagerApi;


PackagerApi apiInstance = new PackagerApi();
try {
    List<ModelPackage> result = apiInstance.packagerGetAllPackages();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PackagerApi#packagerGetAllPackages");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;ModelPackage&gt;**](ModelPackage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="packagerGetSpecificPackages"></a>
# **packagerGetSpecificPackages**
> List&lt;ModelPackage&gt; packagerGetSpecificPackages(searchString)



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PackagerApi;


PackagerApi apiInstance = new PackagerApi();
String searchString = "searchString_example"; // String | 
try {
    List<ModelPackage> result = apiInstance.packagerGetSpecificPackages(searchString);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PackagerApi#packagerGetSpecificPackages");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **searchString** | **String**|  |

### Return type

[**List&lt;ModelPackage&gt;**](ModelPackage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="packagerHealthCheck"></a>
# **packagerHealthCheck**
> String packagerHealthCheck()



### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PackagerApi;


PackagerApi apiInstance = new PackagerApi();
try {
    String result = apiInstance.packagerHealthCheck();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PackagerApi#packagerHealthCheck");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

