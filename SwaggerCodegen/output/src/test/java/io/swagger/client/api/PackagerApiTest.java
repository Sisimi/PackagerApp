package io.swagger.client.api;

import io.swagger.client.ApiClient;
import io.swagger.client.model.ModelPackage;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for PackagerApi
 */
public class PackagerApiTest {

    private PackagerApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(PackagerApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void packagerAddPackageTest() {
        ModelPackage _package = null;
        // ModelPackage response = api.packagerAddPackage(_package);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void packagerGetAllPackagesTest() {
        // List<ModelPackage> response = api.packagerGetAllPackages();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void packagerGetSpecificPackagesTest() {
        String searchString = null;
        // List<ModelPackage> response = api.packagerGetSpecificPackages(searchString);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void packagerHealthCheckTest() {
        // String response = api.packagerHealthCheck();

        // TODO: test validations
    }
}
